package org.cuacfm.contests.api.service;

import java.util.List;

import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.service.custom.BulkDataJSON;
import org.cuacfm.contests.api.service.exception.NotFoundException;

public interface IContestService {

	List<Contest> getAllContests();

	Contest getContestById(String contest);

	Contest createContest(Contest item);

	Contest createContest(String name);

	void updateContestById(String contest, Contest item);

	void deleteContestById(String contest);

	void startVotingContestById(String contest);

	void stopVotingContestById(String contest);

	void bulkLoad(BulkDataJSON data) throws NotFoundException;

}
