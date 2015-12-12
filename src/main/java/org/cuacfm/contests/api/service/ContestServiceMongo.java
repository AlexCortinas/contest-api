package org.cuacfm.contests.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.model.CategoryPostVoting;
import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.IContestRepository;
import org.cuacfm.contests.api.model.RadioShow;
import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.service.custom.BulkDataJSON;
import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class ContestServiceMongo implements ContestService {

	@Inject
	private RadioShowService radioShowService;

	@Inject
	private CategoryService categoryService;

	@Inject
	private CandidateService candidateService;

	@Inject
	private IContestRepository contestRepository;

	@Override
	public List<Contest> getAllContests() {
		return contestRepository.findAll();
	}

	@Override
	public Contest findOne(String contest) throws NotFoundException {
		Contest r = contestRepository.findOne(contest);
		if (r == null)
			throw new NotFoundException("Contest id not found");
		return r;
	}

	@Override
	public Contest save(Contest item) {
		return contestRepository.save(item);
	}

	@Override
	public Contest createContest(String name) {
		Contest r = new Contest(name);
		return save(r);
	}

	@Override
	public void updateContestById(String contest, Contest item) {
		deleteContestById(contest);
		save(item);
	}

	@Override
	public void deleteContestById(String contest) {
		contestRepository.delete(contest);
	}

	@Override
	public void startVotingContestById(String contest) throws NotFoundException {
		Contest c = findOne(contest);
		c.setVoting(true);
		save(c);
	}

	@Override
	public void stopVotingContestById(String contestCode) throws NotFoundException {

		Contest contest = findOne(contestCode);
		contest.setVoting(false);

		HashMap<String, CategoryPostVoting> categories = new HashMap<String, CategoryPostVoting>();
		final Map<String, List<Vote>> allVotesByCategory = new HashMap<String, List<Vote>>();

		for (Category cat : contest.getCategories()) {
			allVotesByCategory.put(cat.getId(), new ArrayList<Vote>());
			categories.put(cat.getId(), new CategoryPostVoting(cat));
		}

		for (RadioShow rs : contest.getShows())
			for (Entry<String, Vote> ent : rs.getVotes().entrySet())
				allVotesByCategory.get(ent.getKey()).add(ent.getValue());

		for (Entry<String, List<Vote>> ent : allVotesByCategory.entrySet()) {
			CategoryPostVoting cpv = categories.get(ent.getKey());
			if (cpv == null) {
				throw new NotFoundException(String.format("Category %s not found", ent.getKey()));
			}
			for (Vote v : ent.getValue()) {
				if (!cpv.getResultsBrute().containsKey(v.getOne())) {
					throw new NotFoundException(
							String.format("Candidate %s for category %s not found", v.getOne(), cpv.getId()));
				}
				addPoints(cpv.getResultsBrute().get(v.getOne()), 1);
				if (!cpv.getResultsBrute().containsKey(v.getTwo())) {
					throw new NotFoundException(
							String.format("Candidate %s for category %s not found", v.getTwo(), cpv.getId()));
				}
				addPoints(cpv.getResultsBrute().get(v.getTwo()), 2);
				if (!cpv.getResultsBrute().containsKey(v.getThree())) {
					throw new NotFoundException(
							String.format("Candidate %s for category %s not found", v.getThree(), cpv.getId()));
				}
				addPoints(cpv.getResultsBrute().get(v.getThree()), 3);
			}
		}

		contest.setCategories(categories.values().stream().collect(Collectors.toList()));
		contestRepository.save(contest);
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
		startVotingContestById(contest);
	}
}
