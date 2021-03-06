package org.cuacfm.contests.api.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.cuacfm.contests.api.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
	private String id;
	private String name;
	private String desc = "";

	private Set<String> candidates = new HashSet<String>();

	public Category() {
	}

	public Category(String name) {
		this.id = StringUtils.normalizeString(name);
		this.name = name;
	}

	public Category(String id, String name, String desc) {
        this.id = StringUtils.normalizeString(id);
		this.name = name;
		this.desc = desc;
	}

	public Category(Category cat) {
		this.id = cat.getId();
		this.name = cat.getName();
		this.desc = cat.getDesc();
		cat.getCandidatesBrute().forEach(s -> this.candidates.add(s));
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

	@JsonProperty
	public List<String> getCandidates() {
		return candidates.stream().sorted().collect(Collectors.toList());
	}

	@JsonIgnore
	public Set<String> getCandidatesBrute() {
		return candidates;
	}

	@JsonIgnore
	public void setCandidates(Set<String> candidates) {
		this.candidates = candidates;
	}

	public Category clone() {
		Category r = new Category(id, name, desc);
		getCandidatesBrute().forEach(s -> r.getCandidatesBrute().add(s));
		return r;
	}

	@Override
	public String toString() {
		return id;
	}

}
