package org.cuacfm.contests.api.service;

import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.contests.api.service.exception.NotFoundException;

public class CandidateServiceMemory implements CandidateService {

	@Inject
	private CategoryService categoryService;

	@Override
	public Set<String> getByContestAndCategory(String contest, String category) throws NotFoundException {
		return categoryService.getCategoryByContestAndId(contest, category).getCandidatesBrute();
	}

	@Override
	public void createNewCandidate(String contest, String category, String item) throws NotFoundException {
		categoryService.getCategoryByContestAndId(contest, category).getCandidatesBrute().add(item);
	}

	@Override
	public void removeCandidate(String contest, String category, String candidate) throws NotFoundException {
		categoryService.getCategoryByContestAndId(contest, category).getCandidatesBrute().removeIf(c -> c.equals(candidate));
	}

}
