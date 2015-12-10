package org.cuacfm.contests.api.rest;

import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Person;
import org.cuacfm.contests.api.service.IPeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contests/{contest}/people")
public class PeopleResource {

	@Inject
	private IPeopleService peopleService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Set<Person>> getAll(@PathVariable String contest) {
		return new ResponseEntity<>(peopleService.getAllByContest(contest), HttpStatus.OK);
	}

}
