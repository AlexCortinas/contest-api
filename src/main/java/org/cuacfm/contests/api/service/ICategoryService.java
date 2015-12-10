package org.cuacfm.contests.api.service;

import java.util.List;

import org.cuacfm.contests.api.model.Category;

public interface ICategoryService {

	List<Category> getAllCategoriesByContest(String contest);

	Category getCategoryByContestAndId(String contest, String category);

	Category createCategory(String contest, Category item);

	Category createCategory(String contest, String name);

	void deleteCategoryByContestAndId(String contest, String category);

	void updateCategoryByContestAndId(String contest, String category, Category item);

}
