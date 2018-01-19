package org.cuacfm.contests.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.RadioShow;
import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.service.custom.CandidateJSON;
import org.cuacfm.contests.api.service.custom.CategoryJSON;
import org.cuacfm.contests.api.service.custom.ContestJSON;
import org.cuacfm.contests.api.service.exception.NotFoundException;

public class RadioShowServiceMemory implements RadioShowService {

	@Inject
	private ContestService contestService;

	@Inject
	private CategoryService categoryService;

	// private static Map<String, Set<RadioShow>> shows = new HashMap<String,
	// Set<RadioShow>>();

	// static {
	// Set<RadioShow> showsOscuacs = new HashSet<RadioShow>();
	// shows.put("OSCUACS15", showsOscuacs);
	// showsOscuacs.add(new RadioShow("Spoiler"));
	// showsOscuacs.add(new RadioShow("Alegría"));
	// showsOscuacs.add(new RadioShow("Falso 9"));
	// }

	@Override
	public Set<RadioShow> getAllShowsByContest(String contest) throws NotFoundException {
		return contestService.findOne(contest).getShows();
	}

	@Override
	public RadioShow getShowByContestAndCode(String contest, String code) throws NotFoundException {
		return contestService.findOne(contest).getShows().stream().filter(s -> s.getCode().equals(code))
				.findFirst().orElse(null);
	}

	@Override
	public RadioShow createShow(String contest, String name) throws NotFoundException {
		RadioShow r = new RadioShow(name);
		while (codeBusy(r.getCode())) {
			r = new RadioShow(name);
		}

		contestService.findOne(contest).getShows().add(r);

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
		contestService.findOne(contest).getShows().removeIf(s -> s.getCode().equals(code));
	}

	@Override
	public void addMember(String contest, String show, String person) throws NotFoundException {
		Optional.ofNullable(getShowByContestAndName(contest, show)).ifPresent(s -> s.getMembers().add(person));
	}

	private RadioShow getShowByContestAndName(String contest, String id) throws NotFoundException {
		return contestService.findOne(contest).getShows().stream().filter(s -> s.getId().equals(id)).findFirst()
				.orElse(null);
	}

	@Override
	public ContestJSON getContestByShowCode(String code) throws NotFoundException {
		Contest c = getContestByShowCodeWithoutModification(code);
        final String contestCode = c.getId();

		if (c == null)
			return null;

		final RadioShow rs = getShowByContestAndCode(c.getId(), code);
		c = c.clone();

		c.getCategories().forEach(cat -> cat.getCandidatesBrute().removeIf(s -> {
			return rs.getName().equals(s) || rs.getMembers().stream().anyMatch(p -> p.equals(s));
		}));
		
        List<CategoryJSON> categories = c.getCategories().stream().map(cat -> {
            Set<CandidateJSON> candidates = cat.getCandidatesBrute().stream().map(cand -> {
                return new CandidateJSON(cand, getCandidateLabel(contestCode, cand));
            }).collect(Collectors.toSet());

            return new CategoryJSON(cat.getId(), cat.getName(), cat.getDesc(), candidates);
        }).collect(Collectors.toList());

        return new ContestJSON(c.getId(), c.getName(), c.getDesc(), c.isVoting(), categories);
	}
	
    private String getCandidateLabel(String contest, String candidate) {
        final List<String> shows = new ArrayList<>();
        
        try {
            contestService.findOne(contest).getShows().forEach(show -> {
                show.getMembers().forEach(member -> {
                    if (member.equals(candidate)) {
                        shows.add(show.getName());
                    }
                });
            });
        } catch (NotFoundException e) {
            return candidate;
        }
        
        if (shows.isEmpty()) return candidate;
        else return candidate + " (" + shows.stream().collect(Collectors.joining(", "))+ ")";
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
		RadioShow show = getShowByCode(code);
		for (Entry<String, Vote> ent : votes.entrySet()) {
			show.getVotes().put(categoryService.getCategoryByContestAndId(c.getId(), ent.getKey()).getId(),
					ent.getValue());
		}
		show.setHasVoted(true);
	}
}
