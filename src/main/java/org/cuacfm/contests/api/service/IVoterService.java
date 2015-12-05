package org.cuacfm.contests.api.service;

import java.util.List;

import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.model.Voter;

public interface IVoterService {

	List<Voter> getAllVotersByContest(String contest);

	List<Voter> generateVoters(String contest, Integer count);

	void disableVoter(String contest, String voter);

	Voter getVoterByContestAndCode(String contest, String voter);

	void voteByContestAndCategoryAndVoter(String contest, String category, String voter, Vote vote);

}
