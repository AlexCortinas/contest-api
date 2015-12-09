package org.cuacfm.contests.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cuacfm.contests.api.model.RadioShow;
import org.springframework.stereotype.Service;

@Service
public class RadioShowServiceMemory implements IRadioShowService {

	private static Map<String, List<RadioShow>> shows = new HashMap<String, List<RadioShow>>();

	static {
		List<RadioShow> showsOscuacs = new ArrayList<RadioShow>();
		shows.put("OSCUACS15", showsOscuacs);
		showsOscuacs.add(new RadioShow("Spoiler"));
		showsOscuacs.add(new RadioShow("Alegría"));
		showsOscuacs.add(new RadioShow("Falso 9"));
	}

	@Override
	public List<RadioShow> getAllShowsByContest(String contest) {
		return shows.get(contest);
	}

	@Override
	public RadioShow getShowByContestAndCode(String contest, String code) {
		return shows.get(contest).stream().filter(s -> s.getName().equals(code)).findFirst().orElse(null);
	}

	@Override
	public RadioShow createShow(String contest, String name) {
		// TODO excepción si el contest no existe
		RadioShow r = new RadioShow(name);
		while (getShowByContestAndCode(contest, r.getCode()) != null) {
			r = new RadioShow(name);
		}

		if (!shows.containsKey(contest)) {
			shows.put(contest, new ArrayList<RadioShow>());
		}

		shows.get(contest).add(r);

		return r;
	}

	@Override
	public void deleteShowByContestAndId(String contest, String code) {
		if (shows.containsKey(contest)) {
			shows.get(contest).removeIf(s -> s.getCode().equals(code));
		}

	}

}
