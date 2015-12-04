package org.cuacfm.contests.api;

import java.util.Collection;
import java.util.Collections;

import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.model.Contest;

public class InMemory {
	public static Contest contest;

	static {
		contest = new Contest();
		contest.setId("OSCUAC2015");
		contest.setName("Oscuacs 2015");
		contest.setDesc("Gala de los Oscuacs después de 3 años");
		contest.getCategories().add(new Category("Mejor locutor"));
		contest.getCategories().add(new Category("Mejor técnico"));
		contest.getCategories().add(new Category("Mejor programa de humor"));
		contest.getCategories().add(new Category("Mejor programa de series"));
	}

	public static Collection<Contest> allContest() {
		return Collections.singleton(contest);
	}
}
