package org.cuacfm.contests.api.service;

import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceMongo implements CandidateService {

	@Inject
	private CategoryService categoryService;

	@Inject
	private ContestService contestService;

	@Override
	public Set<String> getByContestAndCategory(String contest, String category) throws NotFoundException {
		return categoryService.getCategoryByContestAndId(contest, category).getCandidatesBrute();
	}

	@Override
	public void createNewCandidate(String contest, String category, String item) throws NotFoundException {
		Contest cont = contestService.findOne(contest);
		cont.getCategories().stream().filter(c -> c.getId().equals(category)).findFirst().get().getCandidatesBrute()
				.add(item);
		contestService.save(cont);
	}

	@Override
	public void removeCandidate(String contest, String category, String candidate) throws NotFoundException {
		Contest cont = contestService.findOne(contest);
		cont.getCategories().stream().filter(c -> c.getId().equals(category)).findFirst().get().getCandidatesBrute()
				.removeIf(cand -> cand.equals(candidate));
		contestService.save(cont);
	}
}
