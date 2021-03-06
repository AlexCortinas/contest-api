package org.cuacfm.contests.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.model.CategoryPostVoting;
import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.RadioShow;
import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.service.custom.BulkDataJSON;
import org.cuacfm.contests.api.service.exception.NotFoundException;

public class ContestServiceMemory implements ContestService {

	@Inject
	private RadioShowService radioShowService;

	@Inject
	private CategoryService categoryService;

	@Inject
	private CandidateService candidateService;

	private static Map<String, Contest> contests = new HashMap<String, Contest>();

	// static {
	// Contest oscuacs = new Contest("OSCUACS15", "Oscuacs 2015", "Los mejores
	// premios de la emisora");
	// contests.put("OSCUACS15", oscuacs);
	// Category mejorLocutor = new Category("MEJOR_LOCUTOR", "Mejor locutor",
	// "El mejor locutor");
	// Category mejorTecnico = new Category("MEJOR_TECNICO", "Mejor técnico",
	// "El mejor técnico");
	// Category mejorPrograma = new Category("MEJOR_PROGRAMA", "Mejor programa",
	// "El mejor programa");
	// oscuacs.addCategory(mejorTecnico);
	// oscuacs.addCategory(mejorLocutor);
	// oscuacs.addCategory(mejorPrograma);
	// mejorLocutor.getCandidates().add("Diego de la Vega");
	// mejorLocutor.getCandidates().add("Iverson con Ñ");
	// mejorLocutor.getCandidates().add("Isa Lema");
	// mejorTecnico.getCandidates().add("Chema Casanova");
	// mejorTecnico.getCandidates().add("Mariano");
	// mejorPrograma.getCandidates().add("Spoiler");
	// }

	@Override
	public List<Contest> getAllContests() {
		return contests.values().stream().collect(Collectors.toList());
	}

	@Override
	public Contest findOne(String contest) throws NotFoundException {
		if (!contests.containsKey(contest)) {
			throw new NotFoundException("Contest id not found");
		}
		return contests.get(contest);
	}

	@Override
	public Contest save(Contest item) {
		contests.put(item.getId(), item);
		return item;
	}

	@Override
	public Contest createContest(String name) {
		Contest r = new Contest(name);
		return save(r);
	}

	@Override
	public void updateContestById(String contest, Contest item) {
		contests.remove(contest);
		contests.put(item.getId(), item);
	}

	@Override
	public void deleteContestById(String contest) {
		// Exception if contests in voting state
		contests.remove(contest);
	}

	@Override
	public void startVotingContestById(String contest) throws NotFoundException {
		Contest c = findOne(contest);
		c.setVoting(true);
	}

	@Override
	public void stopVotingContestById(String contest) throws NotFoundException {

		Contest c = findOne(contest);
		c.setVoting(false);

		List<Category> categories = new ArrayList<Category>();
		final Map<String, List<Vote>> allVotesByCategory = new HashMap<String, List<Vote>>();

		for (Category cat : c.getCategories())
			allVotesByCategory.put(cat.getId(), new ArrayList<Vote>());

		for (RadioShow rs : radioShowService.getAllShowsByContest(contest))
			for (Entry<String, Vote> ent : rs.getVotes().entrySet())
				allVotesByCategory.get(ent.getKey()).add(ent.getValue());

		allVotesByCategory.entrySet().forEach(ent -> {
			final CategoryPostVoting cpv = new CategoryPostVoting(
					c.getCategories().stream().filter(cat -> cat.getId().equals(ent.getKey())).findFirst().get());
			ent.getValue().forEach(v -> {
				addPoints(cpv.getResultsBrute().get(v.getOne()), 1);
				addPoints(cpv.getResultsBrute().get(v.getTwo()), 2);
				addPoints(cpv.getResultsBrute().get(v.getThree()), 3);
			});
			categories.add(cpv);
		});

		c.setCategories(categories);
	}

	private void addPoints(AtomicInteger i, int points) {
		i.set(i.intValue() + points);
	}

	@Override
	public void bulkLoad(BulkDataJSON data) throws NotFoundException {
		String contest = createContest(data.getName()).getId();
		data.getShows().entrySet().forEach(ent -> {
			try {
				String showId = radioShowService.createShow(contest, ent.getKey()).getId();

				ent.getValue().forEach(p -> {
					try {
						radioShowService.addMember(contest, showId, p);
					} catch (Exception e) {
						// should not happen
						e.printStackTrace();
					}
				});
			} catch (Exception e) {
				// should not happen
				e.printStackTrace();
			}
		});

		data.getCategories().entrySet().forEach(ent -> {
			try {
				String categoryId = categoryService.createCategory(contest, ent.getKey()).getId();
				ent.getValue().forEach(c -> {
					try {
						candidateService.createNewCandidate(contest, categoryId, c);
					} catch (Exception e) {
						// should not happen
						e.printStackTrace();
					}
				});
			} catch (Exception e1) {
				// should not happen
				e1.printStackTrace();
			}
		});
	}

}
