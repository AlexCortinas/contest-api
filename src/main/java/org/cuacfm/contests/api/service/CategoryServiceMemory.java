package org.cuacfm.contests.api.service;

import java.util.List;

import javax.inject.Inject;

import org.cuacfm.contests.api.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceMemory implements ICategoryService {

	@Inject
	private IContestService contestService;

	@Override
	public List<Category> getAllCategoriesByContest(String contest) {
		return contestService.getContestById(contest).getCategories();
	}

	@Override
	public Category getCategoryByContestAndId(String contest, String category) {
		return getAllCategoriesByContest(contest).stream().filter(c -> c.getId().equals(category)).findFirst()
				.orElse(null);
	}

	@Override
	public void createCategory(String contest, Category item) {
		contestService.getContestById(contest).getCategories().add(item);
	}

	@Override
	public void deleteCategoryByContestAndId(String contest, String category) {
		contestService.getContestById(contest).getCategories().removeIf(c -> c.getId().equals(category));
	}

	@Override
	public void updateCategoryByContestAndId(String contest, String category, Category item) {
		deleteCategoryByContestAndId(contest, category);
		createCategory(contest, item);
	}

}
