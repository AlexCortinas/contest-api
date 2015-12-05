package org.cuacfm.contests.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cuacfm.contests.api.model.Candidate;
import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.model.Contest;
import org.springframework.stereotype.Service;

@Service
public class ContestServiceMemory implements IContestService {

	private static Map<String, Contest> contests = new HashMap<String, Contest>();

	static {
		Contest oscuacs = new Contest("OSCUACS15", "Oscuacs 2015", "Los mejores premios de la emisora");
		contests.put("OSCUACS15", oscuacs);
		Category mejorLocutor = new Category("MEJOR_LOCUTOR", "Mejor locutor", "El mejor locutor");
		Category mejorTecnico = new Category("MEJOR_TECNICO", "Mejor técnico", "El mejor técnico");
		oscuacs.getCategories().add(mejorTecnico);
		oscuacs.getCategories().add(mejorLocutor);
		mejorLocutor.getCandidates().add(new Candidate("Diego de la Vega"));
		mejorLocutor.getCandidates().add(new Candidate("Iverson con Ñ"));
		mejorLocutor.getCandidates().add(new Candidate("Isa Lema"));
		mejorTecnico.getCandidates().add(new Candidate("Chema Casanova"));
		mejorTecnico.getCandidates().add(new Candidate("Mariano"));
	}

	@Override
	public List<Contest> getAllContests() {
		return contests.values().stream().collect(Collectors.toList());
	}

	@Override
	public Contest getContestById(String contest) {
		return contests.get(contest);
	}

	@Override
	public void createContest(Contest item) {
		contests.put(item.getId(), item);
	}

	@Override
	public void updateContestById(String contest, Contest item) {
		contests.remove(contest);
		contests.put(item.getId(), item);
	}

	@Override
	public void deleteContestById(String contest) {
		// Exception if contests in voting state
		contests.remove(contest);
	}

	@Override
	public void startVotingContestById(String contest) {
		contests.get(contest).setVoting(true);
	}

	@Override
	public void stopVotingContestById(String contest) {
		// TODO Procesar los votos para dar los resultados por categoría
		contests.get(contest).setVoting(true);
	}

}
