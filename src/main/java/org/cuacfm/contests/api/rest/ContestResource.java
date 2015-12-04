package org.cuacfm.contests.api.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.cuacfm.contests.api.InMemory;
import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.model.ErrorJSON;
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

	@ApiOperation(value = "List all contests")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Contest>> getAll() {
		return ResponseEntity.ok(InMemory.allContest());
	}

	@ApiOperation(value = "Get a contest")
	@RequestMapping(value = "/{contest}", method = RequestMethod.GET)
	public ResponseEntity<Contest> get(@PathVariable String contest) {
		return ResponseEntity.ok(InMemory.contest);
	}

	@ApiOperation(value = "Create a contest")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Contest item) throws URISyntaxException {
		return ResponseEntity.created(new URI(String.format("%s/%s, /api/contests/", item.getId()))).build();
	}

	@ApiOperation(value = "Update a contest")
	@RequestMapping(value = "/{contest}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String contest, @RequestBody Contest item) {
		if (item.getId() == null || item.getId() != contest) {
			return ResponseEntity.badRequest().body(new ErrorJSON("item id does not match uri id"));
		}
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Delete a contest")
	@RequestMapping(value = "/{contest}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String contest) {
	}

	@ApiOperation(value = "Start votation period")
	@RequestMapping(value = "/{contest}/voting", method = RequestMethod.PUT)
	public ResponseEntity<?> startVoting(@PathVariable String contest) {
		return null;
	}

	@ApiOperation(value = "Stop votation period")
	@RequestMapping(value = "/{contest}/voting", method = RequestMethod.DELETE)
	public ResponseEntity<?> stopVoting(@PathVariable String contest) {
		return null;
	}
}
