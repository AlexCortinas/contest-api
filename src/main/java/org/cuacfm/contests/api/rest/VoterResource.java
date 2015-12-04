package org.cuacfm.contests.api.rest;

import java.util.Collection;
import java.util.List;

import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.model.Voter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/contests/{contest}/voters")
public class VoterResource {

	@ApiOperation(value = "List of voters")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Voter>> getAll(@PathVariable String contest) {
		return ResponseEntity.ok(null);
	}

	@ApiOperation(value = "Generates new codes for voting (as many as count parameter indicates)")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<List<Voter>> generateVoters(@PathVariable String contest, @RequestParam Integer count) {
		return ResponseEntity.ok(null);
	}

	@ApiOperation(value = "Disables a voter")
	@RequestMapping(value = "/{voter}", method = RequestMethod.DELETE)
	public ResponseEntity<?> disableVoter(@PathVariable String contest, @PathVariable String voter) {
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Get voter information, including the set of votes already made")
	@RequestMapping(value = "/{voter}", method = RequestMethod.GET)
	public ResponseEntity<Voter> get(@PathVariable String contest, @PathVariable String voter,
			@PathVariable String category) {
		return ResponseEntity.ok(null);
	}

	@ApiOperation(value = "Vote for a category")
	@RequestMapping(value = "/{voter}/category/{category}", method = RequestMethod.PUT)
	public ResponseEntity<?> vote(@PathVariable String contest, @PathVariable String voter,
			@PathVariable String category, @RequestBody Vote vote) {
		return null;
	}
}
