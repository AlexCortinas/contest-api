package org.cuacfm.contests.api.service;

import java.util.List;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceMemory implements ICategoryService {

	@Inject
	private IContestService contestService;

	@Override
	public List<Category> getAllCategoriesByContest(String contest) throws NotFoundException {
		return contestService.getContestById(contest).getCategories();
	}

	@Override
	public Category getCategoryByContestAndId(String contest, String category) throws NotFoundException {
		return getAllCategoriesByContest(contest).stream().filter(c -> c.getId().equals(category)).findFirst()
				.orElseThrow(() -> new NotFoundException("Category not found"));
	}

	@Override
	public Category createCategory(String contest, Category item) throws NotFoundException {
		contestService.getContestById(contest).addCategory(item);
		return item;
	}

	@Override
	public Category createCategory(String contest, String name) throws NotFoundException {
		Category c = new Category(name);
		return createCategory(contest, c);
	}

	@Override
	public void deleteCategoryByContestAndId(String contest, String category) throws NotFoundException {
		contestService.getContestById(contest).getCategories().removeIf(c -> c.getId().equals(category));
	}

	@Override
	public void updateCategoryByContestAndId(String contest, String category, Category item) throws NotFoundException {
		deleteCategoryByContestAndId(contest, category);
		createCategory(contest, item);
	}

}
