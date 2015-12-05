package org.cuacfm.contests.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.model.Voter;
import org.springframework.stereotype.Service;

@Service
public class VoterServiceMemory implements IVoterService {

	private static Map<String, List<Voter>> voters = new HashMap<String, List<Voter>>();

	static {
		List<Voter> votersOscuacs = new ArrayList<Voter>();
		voters.put("OSCUACS15", votersOscuacs);
		for (int i = 0; i < 10; i++) {
			votersOscuacs.add(new Voter(UUID.randomUUID().toString().substring(0, 8)));
		}
	}

	@Override
	public List<Voter> getAllVotersByContest(String contest) {
		return voters.get(contest);
	}

	@Override
	public List<Voter> generateVoters(String contest, Integer count) {
		List<Voter> newVoters = new ArrayList<Voter>();

		for (int i = 0; i < count; i++) {
			newVoters.add(new Voter(UUID.randomUUID().toString().substring(0, 8)));
		}

		if (!voters.containsKey(contest)) {
			voters.put(contest, new ArrayList<Voter>());
		}

		voters.get(contest).addAll(newVoters);

		return newVoters;
	}

	@Override
	public void disableVoter(String contest, String voter) {
		voters.get(contest).removeIf(v -> v.getCode() == voter);
	}

	@Override
	public Voter getVoterByContestAndCode(String contest, String voter) {
		return voters.get(contest).stream().filter(v -> v.getCode() == voter).findFirst().orElse(null);
	}

	@Override
	public void voteByContestAndCategoryAndVoter(String contest, String category, String voter, Vote vote) {
		// TODO no implementado aún
	}

}
