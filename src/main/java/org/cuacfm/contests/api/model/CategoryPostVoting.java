package org.cuacfm.contests.api.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CategoryPostVoting extends Category {
	private Map<Candidate, Integer> results = new HashMap<Candidate, Integer>();

	public Map<Integer, Candidate> getResults() {
		Map<Integer, Candidate> r = new TreeMap<Integer, Candidate>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		results.entrySet().forEach(entry -> {
			r.put(entry.getValue(), entry.getKey());
		});
		return r;
	}
}
