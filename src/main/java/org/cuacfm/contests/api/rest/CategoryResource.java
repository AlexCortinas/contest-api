package org.cuacfm.contests.api.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.service.ICategoryService;
import org.springframework.http.HttpStatus;
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

	@Inject
	private ICategoryService categoryService;

	@ApiOperation(value = "List all categories of the contest")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAll(@PathVariable String contest) {
		return new ResponseEntity<>(categoryService.getAllCategoriesByContest(contest), HttpStatus.OK);
	}

	@ApiOperation(value = "Get a category")
	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public ResponseEntity<Category> get(@PathVariable String contest, @PathVariable String category) {
		return Optional.ofNullable(categoryService.getCategoryByContestAndId(contest, category))
				.map(c -> new ResponseEntity<>(c, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiOperation(value = "Create a category")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@PathVariable String contest, @RequestBody Category item)
			throws URISyntaxException {
		categoryService.createCategory(contest, item);
		return ResponseEntity.created(new URI(String.format("/api/contest/%s/category/%s", contest, item.getId())))
				.build();
	}

	@ApiOperation(value = "Update a category")
	@RequestMapping(value = "/{category}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String contest, @PathVariable String category,
			@RequestBody Category item) {
		categoryService.updateCategoryByContestAndId(contest, category, item);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a category")
	@RequestMapping(value = "/{category}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String contest, @PathVariable String category) {
		categoryService.deleteCategoryByContestAndId(contest, category);
	}

}
