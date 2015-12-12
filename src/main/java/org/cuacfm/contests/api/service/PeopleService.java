package org.cuacfm.contests.api.service;

import java.util.Set;

import org.cuacfm.contests.api.service.exception.NotFoundException;

public interface PeopleService {

	Set<String> getAllByContest(String contest) throws NotFoundException;

}
