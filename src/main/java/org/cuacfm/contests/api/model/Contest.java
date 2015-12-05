package org.cuacfm.contests.api.model;

import java.util.ArrayList;
import java.util.List;

public class Contest {
	private String id;
	private String name;
	private String desc;
	private boolean voting = false;

	private List<Category> categories = new ArrayList<Category>();
	
	public Contest(){}

	public Contest(String id, String name, String desc) {
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public boolean isVoting() {
		return voting;
	}

	public void setVoting(boolean voting) {
		this.voting = voting;
	}
}
