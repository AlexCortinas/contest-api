package org.cuacfm.contests.api.service;

import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceMemory implements ICandidateService {

	@Inject
	private ICategoryService categoryService;

	@Override
	public Set<String> getByContestAndCategory(String contest, String category) throws NotFoundException {
		return categoryService.getCategoryByContestAndId(contest, category).getCandidates();
	}

	@Override
	public void createNewCandidate(String contest, String category, String item) throws NotFoundException {
		categoryService.getCategoryByContestAndId(contest, category).getCandidates().add(item);
	}

	@Override
	public void removeCandidate(String contest, String category, String candidate) throws NotFoundException {
		categoryService.getCategoryByContestAndId(contest, category).getCandidates().removeIf(c -> c.equals(candidate));
	}

}
