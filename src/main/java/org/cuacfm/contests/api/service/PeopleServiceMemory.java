package org.cuacfm.contests.api.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Person;
import org.cuacfm.contests.api.model.RadioShow;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceMemory implements IPeopleService {

	@Inject
	private IRadioShowService radioShowService;

	@Override
	public Set<Person> getAllByContest(String contest) {
		return radioShowService.getAllShowsByContest(contest).stream().map(RadioShow::getMembers)
				.flatMap(l -> l.stream()).collect(Collectors.toSet());
	}

}
