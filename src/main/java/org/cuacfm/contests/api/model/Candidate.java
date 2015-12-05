package org.cuacfm.contests.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Candidate {
	private String name;
	private int votes = 0;

	public Candidate() {
	}

	public Candidate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVotes() {
		return votes;
	}

	@JsonIgnore
	public void setVotes(int votes) {
		this.votes = votes;
	}

}
