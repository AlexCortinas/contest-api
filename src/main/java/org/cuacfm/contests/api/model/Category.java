package org.cuacfm.contests.api.model;

import java.util.ArrayList;
import java.util.Collection;

import org.cuacfm.contests.api.util.StringUtils;

public class Category {
	private String id;
	private String name;
	private String desc;

	private Collection<Candidate> candidates = new ArrayList<Candidate>();

	public Category() {
	}

	public Category(String name) {
		this.id = StringUtils.normalizeString(name);
		this.name = name;
		this.desc = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Collection<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Collection<Candidate> candidates) {
		this.candidates = candidates;
	}

}
