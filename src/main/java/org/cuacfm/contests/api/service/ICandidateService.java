package org.cuacfm.contests.api.service;

import java.util.List;
import java.util.Set;

public interface ICandidateService {

	Set<String> getByContestAndCategory(String contest, String category);

	void createNewCandidate(String contest, String category, String item);

	void removeCandidate(String contest, String category, String item);

}
