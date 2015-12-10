package org.cuacfm.contests.api.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.RadioShow;
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
@RequestMapping("/api/contests/{contest}/shows")
public class RadioShowResource {
	@Inject
	private IRadioShowService radioShowService;

	@ApiOperation(nickname = "List shows", value = "List all shows of the contest")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RadioShow>> getAll(@PathVariable String contest) {
		return new ResponseEntity<>(radioShowService.getAllShowsByContest(contest), HttpStatus.OK);
	}

	@ApiOperation(nickname = "Get show", value = "Get a show by the code")
	@RequestMapping(value = "/{show}", method = RequestMethod.GET)
	public ResponseEntity<RadioShow> get(@PathVariable String contest, @PathVariable String show) {
		return Optional.ofNullable(radioShowService.getShowByContestAndCode(contest, show))
				.map(c -> new ResponseEntity<>(c, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiOperation(nickname = "Create show", value = "Create a show. The only needed parameter is the name.")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@PathVariable String contest, @RequestBody String name) throws URISyntaxException {
		RadioShow r = radioShowService.createShow(contest, name);
		return ResponseEntity.created(new URI(String.format("/api/contest/%s/shows/%s", contest, r.getCode()))).build();
	}

	@ApiOperation(nickname = "Delete show", value = "Delete a show by the code")
	@RequestMapping(value = "/{show}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String contest, @PathVariable String show) {
		radioShowService.deleteShowByContestAndId(contest, show);
	}

	@ApiOperation(nickname = "Vote", value = "Vote")
	@RequestMapping(value = "/{show}/vote", method = RequestMethod.POST)
	public ResponseEntity<?> vote(@PathVariable String contest, @PathVariable String show,
			@RequestBody Map<String, Vote> votes) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
