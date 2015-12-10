package org.cuacfm.contests.api.service;

import java.util.Set;

import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.Person;
import org.cuacfm.contests.api.model.RadioShow;

public interface IRadioShowService {

	Set<RadioShow> getAllShowsByContest(String contest);

	RadioShow getShowByContestAndCode(String contest, String show);

	RadioShow createShow(String contest, String item);

	void deleteShowByContestAndId(String contest, String show);

	void addMember(String contest, String show, Person person);

	Contest getContestByShowCode(String code);

}
