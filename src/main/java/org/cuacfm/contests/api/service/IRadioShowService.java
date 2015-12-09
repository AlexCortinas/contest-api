package org.cuacfm.contests.api.service;

import java.util.List;

import org.cuacfm.contests.api.model.RadioShow;

public interface IRadioShowService {

	List<RadioShow> getAllShowsByContest(String contest);

	RadioShow getShowByContestAndCode(String contest, String show);

	RadioShow createShow(String contest, String item);

	void deleteShowByContestAndId(String contest, String show);

}
