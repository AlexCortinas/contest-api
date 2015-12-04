package org.cuacfm.contests.api.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.cuacfm.contests.api.model.Candidate;
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

	@ApiOperation(value = "List all candidates of the category")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Candidate>> getAll(@PathVariable String contest, @PathVariable String category) {
		return ResponseEntity.ok(null);
	}

	@ApiOperation(value = "Add a new candidate")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@PathVariable String contest, @PathVariable String category,
			@RequestBody Candidate item) throws URISyntaxException {
		return ResponseEntity.created(new URI("")).build();
	}

	@ApiOperation(value = "Remove a candidate")
	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@PathVariable String contest, @PathVariable String category, @RequestBody Candidate item) {
	}
}
