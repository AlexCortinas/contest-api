package org.cuacfm.contests.api.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RadioShow {
	private String name;
	private String code;
	private boolean hasVoted = true;
	private Map<Category, Vote> votes = new HashMap<Category, Vote>();

	public RadioShow() {
		this.code = UUID.randomUUID().toString().substring(0, 8);
	}

	public RadioShow(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public boolean isHasVoted() {
		return hasVoted;
	}

	public Map<Category, Vote> getVotes() {
		return votes;
	}

}
