package org.cuacfm.contests.api.service;

import java.util.List;

public interface ICandidateService {

	List<String> getByContestAndCategory(String contest, String category);

	void createNewCandidate(String contest, String category, String item);

	void removeCandidate(String contest, String category, String item);

}
