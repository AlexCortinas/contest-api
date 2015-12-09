package org.cuacfm.contests.api.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String id;
	private String name;
	private String desc;

	private List<String> candidates = new ArrayList<String>();

	public Category() {
	}

	public Category(String id, String name, String desc) {
		this.id = id;
		this.name = name;
		this.desc = desc;
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

	public List<String> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<String> candidates) {
		this.candidates = candidates;
	}

}
