package org.cuacfm.contests.api.service.custom;

import java.util.List;
import java.util.Map;

public class BulkDataJSON {
	private String name;
	private Map<String, List<String>> shows;
	private Map<String, List<String>> categories;

	public Map<String, List<String>> getShows() {
		return shows;
	}

	public void setShows(Map<String, List<String>> shows) {
		this.shows = shows;
	}

	public Map<String, List<String>> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, List<String>> categories) {
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
