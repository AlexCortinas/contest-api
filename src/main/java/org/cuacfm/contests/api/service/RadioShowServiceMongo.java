package org.cuacfm.contests.api.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.RadioShow;
import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RadioShowServiceMongo implements RadioShowService {

	@Inject
	private ContestService contestService;

	@Override
	public Set<RadioShow> getAllShowsByContest(String contest) throws NotFoundException {
		return contestService.findOne(contest).getShows();
	}

	@Override
	public RadioShow getShowByContestAndCode(String contest, String code) throws NotFoundException {
		return contestService.findOne(contest).getShows().stream().filter(s -> s.getCode().equals(code)).findFirst()
				.orElse(null);
	}

	@Override
	public RadioShow createShow(String contest, String name) throws NotFoundException {
		Contest cont = contestService.findOne(contest);

		RadioShow r = new RadioShow(name);
		while (codeBusy(r.getCode())) {
			r = new RadioShow(name);
		}

		cont.getShows().add(r);
		contestService.save(cont);

		return r;
	}

	private RadioShow getShowByCode(String code) {
		return contestService.getAllContests().stream().map(Contest::getShows).flatMap(l -> l.stream())
				.filter(r -> r.getCode().equals(code)).findFirst().orElse(null);
	}

	private boolean codeBusy(String code) {
		return getShowByCode(code) != null;
	}

	@Override
	public void deleteShowByContestAndId(String contest, String code) throws NotFoundException {
		Contest cont = contestService.findOne(contest);
		cont.getShows().removeIf(s -> s.getCode().equals(code));
		contestService.save(cont);
	}

	@Override
	public void addMember(String contest, String show, String person) throws NotFoundException {
		Contest cont = contestService.findOne(contest);
		RadioShow rshow = cont.getShows().stream().filter(s -> s.getId().equals(show)).findFirst()
				.orElseThrow(() -> new NotFoundException(String.format("Code %s for show not found", show)));
		rshow.getMembers().add(person);
		contestService.save(cont);
	}

	@Override
	public Contest getContestByShowCode(String code) throws NotFoundException {
		Contest c = getContestByShowCodeWithoutModification(code);

		if (c == null)
			return null;

		final RadioShow rs = getShowByContestAndCode(c.getId(), code);
		c = c.clone();

		c.getCategories().forEach(cat -> cat.getCandidates().removeIf(s -> {
			return rs.getName().equals(s) || rs.getMembers().stream().anyMatch(p -> p.equals(s));
		}));

		return c;
	}

	private Contest getContestByShowCodeWithoutModification(String code) {
		for (Contest c : contestService.getAllContests()) {
			if (c.getShows().stream().map(RadioShow::getCode).anyMatch(code::equals)) {
				return c;
			}
		}

		return null;
	}

	@Override
	public void vote(String code, Map<String, Vote> votes) throws NotFoundException {
		Contest c = getContestByShowCodeWithoutModification(code);
		RadioShow show = c.getShows().stream().filter(s -> s.getCode().equals(code)).findFirst()
				.orElseThrow(() -> new NotFoundException(String.format("Code %s for show not found", code)));
		for (Entry<String, Vote> ent : votes.entrySet()) {
			if (!c.getCategories().stream().anyMatch(categ -> categ.getId().equals(ent.getKey()))) {
				new NotFoundException(String.format("Code %s for category not found", ent.getKey()));
			}
			show.getVotes().put(ent.getKey(), ent.getValue());
		}
		show.setHasVoted(true);
		contestService.save(c);
	}
}