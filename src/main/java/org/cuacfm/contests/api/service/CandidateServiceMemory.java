package org.cuacfm.contests.api.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class CandidateServiceMemory implements ICandidateService {

	@Inject
	private ICategoryService categoryService;

	@Override
	public List<String> getByContestAndCategory(String contest, String category) {
		return categoryService.getCategoryByContestAndId(contest, category).getCandidates();
	}

	@Override
	public void createNewCandidate(String contest, String category, String item) {
		categoryService.getCategoryByContestAndId(contest, category).getCandidates().add(item);
	}

	@Override
	public void removeCandidate(String contest, String category, String candidate) {
		categoryService.getCategoryByContestAndId(contest, category).getCandidates().removeIf(c -> c.equals(candidate));
	}

}
