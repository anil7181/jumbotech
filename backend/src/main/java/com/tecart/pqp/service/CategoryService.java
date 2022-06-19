package com.tecart.pqp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.entity.product.Category;
import com.tecart.pqp.repository.CategoryRepository;
import com.tecart.pqp.utils.constants.ClassPropertiesNameConstants;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@org.springframework.stereotype.Service
public class CategoryService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonService commonService;

	@Autowired
	private CategoryRepository categoryRepository;

	static Specification<Category> containsCategoryName(String name) {
		return (category, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(category.get(ClassPropertiesNameConstants.CLASS_PROPERTY_NAME)),
				"%" + name.toUpperCase() + "%");
	}

	static Specification<Category> containsCode(String code) {
		return (category, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(category.get(ClassPropertiesNameConstants.CLASS_PROPERTY_CODE)),
				"%" + code.toUpperCase() + "%");
	}

	static Specification<Category> categoryCategoryId(int id) {
		return (category, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(category.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ID), id);
	}

	private Specification<Category> isCategoryActive(int active) {
		return (category, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(category.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ACTIVE_FLAG), active);
	}

	public Page<Category> getListOfAllCategory(Category category, Integer pageNo, String sortKey,
			Integer recordsPerPage, String sortDir, User loggedInUser) {
		logger.info("Inside getListOfAllCategory method in service");

		Sort sort = null;
		if (sortDir.equals("desc")) {
			sort = Sort.by(Sort.Direction.DESC, sortKey);
		} else {
			sort = Sort.by(Sort.Direction.ASC, sortKey);
		}

		Pageable page = PageRequest.of(pageNo, recordsPerPage, sort);
		Page<Category> categoryPageableList = categoryRepository.findAll(searchCriteriaForCategory(category, loggedInUser),
				page);

		return categoryPageableList;
	}

	private Specification<Category> searchCriteriaForCategory(Category category, User currentLoggedInUser) {
		logger.info("Inside searchCriteriaForCategory method in service");

		return Specification
				.where(commonService.isGivenStringNtEmptyAndNtNull(category.getName())
						? containsCategoryName(category.getName())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(category.getCode())
						? containsCategoryName(category.getCode())
						: null)
				.and(category.getId() > 0 ? categoryCategoryId(category.getId()) : null)
				.and(category.getActive() > 0 ? isCategoryActive(category.getActive()) : null);
	}

	public Category saveOrUpdateCategory(Category category) {
		logger.info("Inside saveOrUpdateCategory method in service");

		Category savedCategory = categoryRepository.saveAndFlush(category);

		return savedCategory;
	}

	public Category loadCategoryById(Integer id, User loggedInUser) {
		logger.info("Inside loadCategoryById method in service");
		Category categoryById = null;

		Optional<Category> optionalCategory = categoryRepository
				.findOne(searchCriteriaForCategory(new Category(id), loggedInUser));

		if (optionalCategory.isPresent()) {
			categoryById = optionalCategory.get();
		}

		return categoryById;
	}

	public void deleteCategoryById(Integer id, User loggedInUser) {
		logger.info("Inside deleteCategoryById method in service");
		Category categoryToBeDeleted = loadCategoryById(id, loggedInUser);

		if (categoryToBeDeleted != null) {
			categoryRepository.delete(categoryToBeDeleted);
		} else {
			String recordNotFoundMessage = "Category not found for Category Id - " + id;
			logger.info(recordNotFoundMessage);
			throw new RecordNotFoundException(recordNotFoundMessage);
		}
	}
}
