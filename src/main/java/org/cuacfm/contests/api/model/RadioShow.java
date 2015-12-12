package org.cuacfm.contests.api.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.cuacfm.contests.api.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RadioShow {
	private String id;
	private String name;
	private String code;
	private boolean hasVoted = false;
	private Map<String, Vote> votes = new HashMap<String, Vote>();
	private Set<String> members = new HashSet<String>();

	public RadioShow() {
		this.code = UUID.randomUUID().toString().substring(0, 8);
	}

	public RadioShow(String name) {
		this();
		this.id = StringUtils.normalizeString(name);
		this.name = name;
		if (this.name.equals("admin")) {
			this.code = "admin";
		}
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

	public String getCode() {
		return code;
	}

	@JsonProperty
	public boolean isHasVoted() {
		return hasVoted;
	}

	@JsonIgnore
	public void setHasVoted(boolean v) {
		this.hasVoted = v;
	}

	public Map<String, Vote> getVotes() {
		return votes;
	}

	public Set<String> getMembers() {
		return members;
	}

	public void setMembers(Set<String> members) {
		this.members = members;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RadioShow other = (RadioShow) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
