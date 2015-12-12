package org.cuacfm.contests.api.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryPostVoting extends Category {
	private Map<String, AtomicInteger> results = new HashMap<String, AtomicInteger>();

	public CategoryPostVoting() {
	}

	public CategoryPostVoting(Category cat) {
		super(cat);
		this.getCandidatesBrute().forEach(s -> results.put(s, new AtomicInteger(0)));
	}

	@JsonIgnore
	public Map<String, AtomicInteger> getResultsBrute() {
		return results;
	}

	static <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}

	public List<Entry<String, Integer>> getResults() {
		Map<String, Integer> r = new HashMap<String, Integer>();
		results.entrySet().forEach(ent -> r.put(ent.getKey(), ent.getValue().intValue()));
		return entriesSortedByValues(r);
	}
}
