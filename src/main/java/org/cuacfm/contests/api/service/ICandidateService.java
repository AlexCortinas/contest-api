package org.cuacfm.contests.api.service;

import java.util.List;

import org.cuacfm.contests.api.model.Candidate;

public interface ICandidateService {

	List<Candidate> getByContestAndCategory(String contest, String category);

	void createNewCandidate(String contest, String category, Candidate item);

	void removeCandidate(String contest, String category, String item);


}
