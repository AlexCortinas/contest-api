package org.cuacfm.contests.api.rest;

import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Vote;
import org.cuacfm.contests.api.service.IRadioShowService;
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
	private IRadioShowService radioShowService;

	@ApiOperation(nickname = "Get the contest info", value = "Get the contest info")
	@RequestMapping(value = "/{code}/contest", method = RequestMethod.POST)
	public ResponseEntity<?> vote(@PathVariable String code) {
		return Optional.ofNullable(radioShowService.getContestByShowCode(code))
				.map(c -> new ResponseEntity<>(c, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiOperation(nickname = "Vote", value = "Vote")
	@RequestMapping(value = "/{show}/vote", method = RequestMethod.POST)
	public ResponseEntity<?> vote(@PathVariable String contest, @PathVariable String show,
			@RequestBody Map<String, Vote> votes) {

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
