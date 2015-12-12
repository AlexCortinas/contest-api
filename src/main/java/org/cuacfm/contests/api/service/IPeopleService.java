package org.cuacfm.contests.api.service;

import java.util.Set;

public interface IPeopleService {

	Set<String> getAllByContest(String contest);

}
