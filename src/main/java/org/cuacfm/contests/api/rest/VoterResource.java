package org.cuacfm.contests.api.rest;

import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.service.RadioShowService;
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
@RequestMapping(value = "/api/voter")
public class VoterResource {

	@Inject
	private RadioShowService radioShowService;

	@ApiOperation(nickname = "Get the contest info", value = "Get the contest info")
	@RequestMapping(value = "/{code}/contest", method = RequestMethod.POST)
	public ResponseEntity<?> vote(@PathVariable String code) throws NotFoundException {
		return Optional.ofNullable(radioShowService.getContestByShowCode(code))
				.map(c -> new ResponseEntity<>(c, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiOperation(nickname = "Vote", value = "Vote")
	@RequestMapping(value = "/{code}/vote", method = RequestMethod.POST)
	public ResponseEntity<?> vote(@PathVariable String code, @RequestBody Map<String, Vote> votes)
			throws NotFoundException {
		if (code.equals("admin"))
			return null;
		radioShowService.vote(code, votes);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
