package org.cuacfm.contests.api.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sun.java2d.xr.MutableInteger;

public class CategoryPostVoting extends Category {
	private Map<String, MutableInteger> results = new HashMap<String, MutableInteger>();

	public CategoryPostVoting() {
	}

	public CategoryPostVoting(Category cat) {
		super(cat);
		this.getCandidates().forEach(s -> results.put(s, new MutableInteger(0)));
	}

	@JsonIgnore
	public Map<String, MutableInteger> getResultsBrute() {
		return results;
	}

	public Map<Integer, String> getResults() {
		Map<Integer, String> r = new TreeMap<Integer, String>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		results.entrySet().forEach(entry -> {
			r.put(entry.getValue().getValue(), entry.getKey());
		});
		return r;
	}
}
