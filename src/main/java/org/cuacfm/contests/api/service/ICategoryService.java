package org.cuacfm.contests.api.service;

import java.util.List;

import org.cuacfm.contests.api.model.Category;

public interface ICategoryService {

	List<Category> getAllCategoriesByContest(String contest);

	Category getCategoryByContestAndId(String contest, String category);

	void createCategory(String contest, Category item);

	void deleteCategoryByContestAndId(String contest, String category);

	void updateCategoryByContestAndId(String contest, String category, Category item);

}
