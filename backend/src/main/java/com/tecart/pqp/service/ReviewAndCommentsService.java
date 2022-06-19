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
import org.springframework.stereotype.Service;

import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.entity.product.ReviewAndComments;
import com.tecart.pqp.repository.ReviewAndCommentsRepo;
import com.tecart.pqp.utils.constants.ClassPropertiesNameConstants;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@Service
public class ReviewAndCommentsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonService commonService;

	@Autowired
	private ReviewAndCommentsRepo reviewAndCommentsRepository;

	static Specification<ReviewAndComments> containsReviewAndCommentsUserName(String userName) {
		return (reviewAndComments, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder
						.upper(reviewAndComments.get(ClassPropertiesNameConstants.CLASS_PROPERTY_REVIEW_USER_NAME)),
				"%" + userName.toUpperCase() + "%");
	}

	static Specification<ReviewAndComments> containsReviewAndCommentsReviewedFor(String reviewedFor) {
		return (reviewAndComments, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder
						.upper(reviewAndComments.get(ClassPropertiesNameConstants.CLASS_PROPERTY_REVIEW_REVIEWED_FOR)),
				"%" + reviewedFor.toUpperCase() + "%");
	}

	static Specification<ReviewAndComments> reviewAndCommentsReviewAndCommentsId(int id) {
		return (reviewAndComments, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(reviewAndComments.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ID), id);
	}

	private Specification<ReviewAndComments> isReviewAndCommentsActive(int active) {
		return (reviewAndComments, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(reviewAndComments.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ACTIVE_FLAG), active);
	}

	static Specification<ReviewAndComments> reviewAndCommentsHasRatings(Double ratings) {
		return (reviewAndComments, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(reviewAndComments.get(ClassPropertiesNameConstants.CLASS_PROPERTY_REVIEW_RATINGS), ratings);
	}

	public Page<ReviewAndComments> getListOfAllReviewAndComments(ReviewAndComments reviewAndComments, Integer pageNo,
			String sortKey, Integer recordsPerPage, String sortDir, User loggedInUser) {
		logger.info("Inside getListOfAllReviewAndComments method in service");

		Sort sort = null;
		if (sortDir.equals("desc")) {
			sort = Sort.by(Sort.Direction.DESC, sortKey);
		} else {
			sort = Sort.by(Sort.Direction.ASC, sortKey);
		}

		Pageable page = PageRequest.of(pageNo, recordsPerPage, sort);
		Page<ReviewAndComments> reviewAndCommentsPageableList = reviewAndCommentsRepository
				.findAll(searchCriteriaForReviewAndComments(reviewAndComments, loggedInUser), page);

		return reviewAndCommentsPageableList;
	}

	private Specification<ReviewAndComments> searchCriteriaForReviewAndComments(ReviewAndComments reviewAndComments,
			User currentLoggedInUser) {
		logger.info("Inside searchCriteriaForReviewAndComments method in service");

		return Specification
				.where(commonService.isGivenStringNtEmptyAndNtNull(reviewAndComments.getUserName())
						? containsReviewAndCommentsUserName(reviewAndComments.getUserName())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(reviewAndComments.getReviewedFor())
						? containsReviewAndCommentsReviewedFor(reviewAndComments.getReviewedFor())
						: null)
				.and(reviewAndComments.getId() > 0 ? reviewAndCommentsReviewAndCommentsId(reviewAndComments.getId())
						: null)
				.and(reviewAndComments.getRatings() > 0 ? reviewAndCommentsHasRatings(reviewAndComments.getRatings())
						: null)
				.and(reviewAndComments.getActive() > 0 ? isReviewAndCommentsActive(reviewAndComments.getActive())
						: null);
	}

	public ReviewAndComments saveOrUpdateReviewAndComments(ReviewAndComments reviewAndComments) {
		logger.info("Inside saveOrUpdateReviewAndComments method in service");

		ReviewAndComments savedReviewAndComments = reviewAndCommentsRepository.saveAndFlush(reviewAndComments);

		return savedReviewAndComments;
	}

	public ReviewAndComments loadReviewAndCommentsById(Integer id, User loggedInUser) {
		logger.info("Inside loadReviewAndCommentsById method in service");
		ReviewAndComments reviewAndCommentsById = null;

		Optional<ReviewAndComments> optionalReviewAndComments = reviewAndCommentsRepository
				.findOne(searchCriteriaForReviewAndComments(new ReviewAndComments(id), loggedInUser));

		if (optionalReviewAndComments.isPresent()) {
			reviewAndCommentsById = optionalReviewAndComments.get();
		}

		return reviewAndCommentsById;
	}

	public void deleteReviewAndCommentsById(Integer id, User loggedInUser) {
		logger.info("Inside deleteReviewAndCommentsById method in service");
		ReviewAndComments reviewAndCommentsToBeDeleted = loadReviewAndCommentsById(id, loggedInUser);

		if (reviewAndCommentsToBeDeleted != null) {
			reviewAndCommentsRepository.delete(reviewAndCommentsToBeDeleted);
		} else {
			String recordNotFoundMessage = "ReviewAndComments not found for ReviewAndComments Id - " + id;
			logger.info(recordNotFoundMessage);
			throw new RecordNotFoundException(recordNotFoundMessage);
		}
	}
}
