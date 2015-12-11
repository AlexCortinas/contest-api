package org.cuacfm.contests.api.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryPostVoting extends Category {
	private Map<String, AtomicInteger> results = new HashMap<String, AtomicInteger>();

	public CategoryPostVoting() {
	}

	public CategoryPostVoting(Category cat) {
		super(cat);
		this.getCandidates().forEach(s -> results.put(s, new AtomicInteger(0)));
	}

	@JsonIgnore
	public Map<String, AtomicInteger> getResultsBrute() {
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
			r.put(entry.getValue().intValue(), entry.getKey());
		});
		return r;
	}
}
