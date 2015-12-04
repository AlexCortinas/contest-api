package org.cuacfm.contests.api.model;

import java.util.Map;

public class Voter {
	private String code;
	private Map<Category, Vote> votes;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<Category, Vote> getVotes() {
		return votes;
	}

	public void setVotes(Map<Category, Vote> votes) {
		this.votes = votes;
	}
}
