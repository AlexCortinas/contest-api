package org.cuacfm.contests.api.model;

import java.util.ArrayList;
import java.util.Collection;

public class Contest {
	private String id;
	private String name;
	private String desc;
	private boolean voting = false;

	private Collection<Category> categories = new ArrayList<Category>();

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

	public Collection<Category> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

	public boolean isVoting() {
		return voting;
	}

	public void setVoting(boolean voting) {
		this.voting = voting;
	}
}
