package org.cuacfm.contests.api.model;

import java.util.HashMap;
import java.util.Map;

public class Voter {
	private String code;
	private Map<Category, Vote> votes = new HashMap<Category, Vote>();

	public Voter() {
	}

	public Voter(String substring) {
		this.code = substring;
	}

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
