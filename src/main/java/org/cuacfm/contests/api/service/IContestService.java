package org.cuacfm.contests.api.service;

import java.util.List;

import org.cuacfm.contests.api.model.Contest;

public interface IContestService {

	List<Contest> getAllContests();

	Contest getContestById(String contest);

	void createContest(Contest item);

	void updateContestById(String contest, Contest item);

	void deleteContestById(String contest);

	void startVotingContestById(String contest);

	void stopVotingContestById(String contest);

}
