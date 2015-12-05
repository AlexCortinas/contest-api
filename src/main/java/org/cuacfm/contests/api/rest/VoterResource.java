package org.cuacfm.contests.api.rest;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.model.Voter;
import org.cuacfm.contests.api.service.IVoterService;
import org.springframework.http.HttpStatus;
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

	@Inject
	private IVoterService voterService;

	@ApiOperation(value = "List of voters")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Voter>> getAll(@PathVariable String contest) {
		return new ResponseEntity<>(voterService.getAllVotersByContest(contest), HttpStatus.OK);
	}

	@ApiOperation(value = "Generates new codes for voting (as many as count parameter indicates)")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<List<Voter>> generateVoters(@PathVariable String contest, @RequestParam Integer count) {
		return new ResponseEntity<>(voterService.generateVoters(contest, count), HttpStatus.OK);
	}

	@ApiOperation(value = "Disables a voter")
	@RequestMapping(value = "/{voter}", method = RequestMethod.DELETE)
	public ResponseEntity<?> disableVoter(@PathVariable String contest, @PathVariable String voter) {
		voterService.disableVoter(contest, voter);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get voter information, including the set of votes already made")
	@RequestMapping(value = "/{voter}", method = RequestMethod.GET)
	public ResponseEntity<Voter> get(@PathVariable String contest, @PathVariable String voter,
			@PathVariable String category) {
		return Optional.ofNullable(voterService.getVoterByContestAndCode(contest, voter))
				.map(v -> new ResponseEntity<>(v, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiOperation(value = "Vote for a category")
	@RequestMapping(value = "/{voter}/category/{category}", method = RequestMethod.PUT)
	public ResponseEntity<?> vote(@PathVariable String contest, @PathVariable String voter,
			@PathVariable String category, @RequestBody Vote vote) {
		voterService.voteByContestAndCategoryAndVoter(contest, category, voter, vote);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
