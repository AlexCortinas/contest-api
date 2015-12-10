package org.cuacfm.contests.api.service;

import java.util.Set;

import org.cuacfm.contests.api.model.Person;

public interface IPeopleService {

	Set<Person> getAllByContest(String contest);

}
