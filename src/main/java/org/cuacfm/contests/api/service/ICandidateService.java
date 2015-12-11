package org.cuacfm.contests.api.service;

import java.util.Set;

import org.cuacfm.contests.api.service.exception.NotFoundException;

public interface ICandidateService {

	Set<String> getByContestAndCategory(String contest, String category) throws NotFoundException;

	void createNewCandidate(String contest, String category, String item) throws NotFoundException;

	void removeCandidate(String contest, String category, String item) throws NotFoundException;

}
