package org.cuacfm.contests.api.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CategoryPostVoting extends Category {
	private Map<Person, Integer> results = new HashMap<Person, Integer>();

	public Map<Integer, Person> getResults() {
		Map<Integer, Person> r = new TreeMap<Integer, Person>(new Comparator<Integer>() {
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
