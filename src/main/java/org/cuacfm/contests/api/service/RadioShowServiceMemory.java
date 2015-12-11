package org.cuacfm.contests.api.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.Person;
import org.cuacfm.contests.api.model.RadioShow;
import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RadioShowServiceMemory implements IRadioShowService {

	@Inject
	private IContestService contestService;

	@Inject
	private ICategoryService categoryService;

	private static Map<String, Set<RadioShow>> shows = new HashMap<String, Set<RadioShow>>();

	static {
		Set<RadioShow> showsOscuacs = new HashSet<RadioShow>();
		shows.put("OSCUACS15", showsOscuacs);
		showsOscuacs.add(new RadioShow("Spoiler"));
		showsOscuacs.add(new RadioShow("Alegría"));
		showsOscuacs.add(new RadioShow("Falso 9"));
	}

	@Override
	public Set<RadioShow> getAllShowsByContest(String contest) {
		return shows.get(contest);
	}

	@Override
	public RadioShow getShowByContestAndCode(String contest, String code) {
		if (!shows.containsKey(contest) && contestService.getContestById(contest) != null) {
			shows.put(contest, new HashSet<RadioShow>());
		}
		return shows.get(contest).stream().filter(s -> s.getCode().equals(code)).findFirst().orElse(null);
	}

	@Override
	public RadioShow createShow(String contest, String name) {
		// TODO excepción si el contest no existe
		RadioShow r = new RadioShow(name);
		while (codeBusy(r.getCode())) {
			r = new RadioShow(name);
		}

		if (!shows.containsKey(contest)) {
			shows.put(contest, new HashSet<RadioShow>());
		}

		shows.get(contest).add(r);

		return r;
	}

	private RadioShow getShowByCode(String code) {
		return shows.values().stream().flatMap(l -> l.stream()).filter(r -> r.getCode().equals(code)).findFirst()
				.orElse(null);
	}

	private boolean codeBusy(String code) {
		return getShowByCode(code) != null;
	}

	@Override
	public void deleteShowByContestAndId(String contest, String code) {
		if (shows.containsKey(contest)) {
			shows.get(contest).removeIf(s -> s.getCode().equals(code));
		}
	}

	@Override
	public void addMember(String contest, String show, Person person) {
		Optional.ofNullable(getShowByContestAndName(contest, show)).ifPresent(s -> s.getMembers().add(person));
	}

	private RadioShow getShowByContestAndName(String contest, String id) {
		if (!shows.containsKey(contest) && contestService.getContestById(contest) != null) {
			shows.put(contest, new HashSet<RadioShow>());
		}
		return shows.get(contest).stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public Contest getContestByShowCode(String code) {
		Contest c = getContestByShowCodeWithoutModification(code);

		if (c == null)
			return null;

		final RadioShow rs = getShowByContestAndCode(c.getId(), code);
		c = c.clone();

		c.getCategories().forEach(cat -> cat.getCandidates().removeIf(s -> {
			return rs.getName().equals(s) || rs.getMembers().stream().anyMatch(p -> p.getName().equals(s));
		}));

		return c;
	}

	private Contest getContestByShowCodeWithoutModification(String code) {
		for (Entry<String, Set<RadioShow>> entry : shows.entrySet()) {
			if (entry.getValue().stream().map(RadioShow::getCode).anyMatch(code::equals))
				return contestService.getContestById(entry.getKey());
		}
		return null;
	}

	@Override
	public void vote(String code, Map<String, Vote> votes) throws NotFoundException {
		Contest c = getContestByShowCodeWithoutModification(code);
		RadioShow show = getShowByCode(code);
		for (Entry<String, Vote> ent : votes.entrySet()) {
			show.getVotes().put(categoryService.getCategoryByContestAndId(c.getId(), ent.getKey()).getId(),
					ent.getValue());
		}
		show.setHasVoted(true);
	}
}
