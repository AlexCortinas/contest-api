package org.cuacfm.contests.api.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.model.ErrorJSON;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/contests/{contest}/categories")
public class CategoryResource {

	@ApiOperation(value = "List all categories of the contest")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Category>> getAll(@PathVariable String contest) {
		return ResponseEntity.ok(null);
	}

	@ApiOperation(value = "Get a category")
	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public ResponseEntity<Category> get(@PathVariable String contest, @PathVariable String category) {
		return ResponseEntity.ok(null);
	}

	@ApiOperation(value = "Create a category")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@PathVariable String contest, @RequestBody Category item)
			throws URISyntaxException {
		return ResponseEntity.created(new URI("")).build();
	}

	@ApiOperation(value = "Update a category")
	@RequestMapping(value = "/{category}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String contest, @PathVariable String category,
			@RequestBody Category item) {
		if (item.getId() == null || item.getId() != category) {
			return ResponseEntity.badRequest().body(new ErrorJSON("item id does not match uri id"));
		}
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Delete a category")
	@RequestMapping(value = "/{category}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String contest, @PathVariable String category) {
	}

}
