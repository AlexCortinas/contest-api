package org.cuacfm.contests.api.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.ValueJSON;
import org.cuacfm.contests.api.service.ICandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/contests/{contest}/categories/{category}/candidates")
public class CandidateResource {

	@Inject
	private ICandidateService candidateService;

	@ApiOperation(nickname = "List candidates", value = "List all candidates of the category")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Set<String>> getAll(@PathVariable String contest, @PathVariable String category) {
		return new ResponseEntity<>(candidateService.getByContestAndCategory(contest, category), HttpStatus.OK);
	}

	@ApiOperation(nickname = "Add candidate", value = "Add a new candidate")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@PathVariable String contest, @PathVariable String category,
			@RequestBody ValueJSON item) throws URISyntaxException {
		candidateService.createNewCandidate(contest, category, item.getValue());
		return ResponseEntity
				.created(new URI(String.format("/api/contest/%s/category/%s/candidates", contest, category))).build();
	}

	@ApiOperation(nickname = "Remove candidate", value = "Remove a candidate")
	@RequestMapping(method = RequestMethod.DELETE)
	public void remove(@PathVariable String contest, @PathVariable String category, @RequestBody ValueJSON item) {
		candidateService.removeCandidate(contest, category, item.getValue());
	}
}
