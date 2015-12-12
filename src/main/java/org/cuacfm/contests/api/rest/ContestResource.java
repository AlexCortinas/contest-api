package org.cuacfm.contests.api.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.ValueJSON;
import org.cuacfm.contests.api.service.ContestService;
import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/contests")
public class ContestResource {

	@Inject
	private ContestService contestService;

	@ApiOperation(nickname = "List contests", value = "List all contests")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Contest>> getAll() {
		return new ResponseEntity<>(contestService.getAllContests(), HttpStatus.OK);
	}

	@ApiOperation(nickname = "Get contest", value = "Get a contest")
	@RequestMapping(value = "/{contest}", method = RequestMethod.GET)
	public ResponseEntity<Contest> get(@PathVariable String contest) throws NotFoundException {
		return Optional.ofNullable(contestService.findOne(contest))
				.map(c -> new ResponseEntity<>(c, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiOperation(nickname = "Create contest", value = "Create a contest")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Contest item) throws URISyntaxException {
		contestService.save(item);
		return ResponseEntity.created(new URI(String.format("/api/contests/%s", item.getId()))).build();
	}

	@ApiOperation(nickname = "Create contest by name", value = "Create a contest just by name")
	@RequestMapping(value = "/quick", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody ValueJSON name) throws URISyntaxException {
		Contest c = contestService.createContest(name.getValue());
		return ResponseEntity.created(new URI(String.format("/api/contests/%s", c.getId()))).build();
	}

	@ApiOperation(nickname = "Update contest", value = "Update a contest")
	@RequestMapping(value = "/{contest}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String contest, @RequestBody Contest item) {
		contestService.updateContestById(contest, item);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(nickname = "Delete contest", value = "Delete a contest")
	@RequestMapping(value = "/{contest}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String contest) {
		contestService.deleteContestById(contest);
	}

	@ApiOperation(nickname = "Start votations", value = "Start votation period")
	@RequestMapping(value = "/{contest}/voting", method = RequestMethod.PUT)
	public ResponseEntity<?> startVoting(@PathVariable String contest) throws NotFoundException {
		contestService.startVotingContestById(contest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(nickname = "Stop votations", value = "Stop votation period")
	@RequestMapping(value = "/{contest}/voting", method = RequestMethod.DELETE)
	public ResponseEntity<?> stopVoting(@PathVariable String contest) throws NotFoundException {
		contestService.stopVotingContestById(contest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
