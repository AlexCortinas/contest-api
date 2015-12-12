package org.cuacfm.contests.api.service;

import java.util.Map;
import java.util.Set;

import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.RadioShow;
import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.service.exception.NotFoundException;

public interface RadioShowService {

	Set<RadioShow> getAllShowsByContest(String contest) throws NotFoundException;

	RadioShow getShowByContestAndCode(String contest, String show) throws NotFoundException;

	RadioShow createShow(String contest, String item) throws NotFoundException;

	void deleteShowByContestAndId(String contest, String show) throws NotFoundException;

	void addMember(String contest, String show, String person) throws NotFoundException;

	Contest getContestByShowCode(String code) throws NotFoundException;

	void vote(String code, Map<String, Vote> votes) throws NotFoundException;

}
