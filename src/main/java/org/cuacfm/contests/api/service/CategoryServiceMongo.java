package org.cuacfm.contests.api.service;

import java.util.List;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.model.Contest;
import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceMongo implements CategoryService {

	@Inject
	private ContestService contestService;

	@Override
	public List<Category> getAllCategoriesByContest(String contest) throws NotFoundException {
		return contestService.findOne(contest).getCategories();
	}

	@Override
	public Category getCategoryByContestAndId(String contest, String category) throws NotFoundException {
		return getAllCategoriesByContest(contest).stream().filter(c -> c.getId().equals(category)).findFirst()
				.orElseThrow(() -> new NotFoundException("Category not found"));
	}

	@Override
	public Category createCategory(String contest, Category item) throws NotFoundException {
		Contest c = contestService.findOne(contest);
		c.addCategory(item);
		contestService.save(c);
		return item;
	}

	@Override
	public Category createCategory(String contest, String name) throws NotFoundException {
		Category c = new Category(name);
		return createCategory(contest, c);
	}

	@Override
	public void deleteCategoryByContestAndId(String contest, String category) throws NotFoundException {
		Contest cont = contestService.findOne(contest);
		cont.getCategories().removeIf(c -> c.getId().equals(category));
		contestService.save(cont);
	}

	@Override
	public void updateCategoryByContestAndId(String contest, String category, Category item) throws NotFoundException {
		deleteCategoryByContestAndId(contest, category);
		createCategory(contest, item);
	}

}
