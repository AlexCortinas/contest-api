package org.cuacfm.contests.api.service;

import java.util.List;

import org.cuacfm.contests.api.model.Category;
import org.cuacfm.contests.api.service.exception.NotFoundException;

public interface CategoryService {

	List<Category> getAllCategoriesByContest(String contest) throws NotFoundException;

	Category getCategoryByContestAndId(String contest, String category) throws NotFoundException;

	Category createCategory(String contest, Category item) throws NotFoundException;

	Category createCategory(String contest, String name) throws NotFoundException;

	void deleteCategoryByContestAndId(String contest, String category) throws NotFoundException;

	void updateCategoryByContestAndId(String contest, String category, Category item) throws NotFoundException;

}
