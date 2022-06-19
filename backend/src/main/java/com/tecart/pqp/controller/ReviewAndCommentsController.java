package com.tecart.pqp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.entity.product.ReviewAndComments;
import com.tecart.pqp.service.CommonService;
import com.tecart.pqp.service.ReviewAndCommentsService;
import com.tecart.pqp.utils.constants.MasterConstants;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = MasterConstants.ROOT_API_PATH)
public class ReviewAndCommentsController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonService commonService;

	@Autowired
	private ReviewAndCommentsService reviewAndCommentsService;

	@PreAuthorize("hasAnyAuthority('READ_ORG', 'SUPPER_ADMIN_API', 'ADMIN_API')")
	@GetMapping({ "/reviewAndComments" })
	public Page<ReviewAndComments> getListOfAllUser(
			@RequestParam(value = "pageNo", defaultValue = MasterConstants.DEFAULT_PAGE_NO_VALUE) Integer pageNo,
			@RequestParam(value = "sortKey", defaultValue = MasterConstants.DEFAULT_SORT_KEY) String sortKey,
			@RequestParam(value = "recordsPerPage", defaultValue = MasterConstants.DEFAULT_RECORDS_PER_PAGE_VALUE) Integer recordsPerPage,
			@RequestParam(value = "sortDir", defaultValue = MasterConstants.DEFAULT_SORT_DIR) String sortDir,
			@RequestParam(value = "body") String reviewAndCommentsParam) throws JsonProcessingException {
		logger.info("Inside getListOfAllUser method in controller");

		ObjectMapper objectMapper = new ObjectMapper();
		ReviewAndComments reviewAndComments = objectMapper.readValue(reviewAndCommentsParam, ReviewAndComments.class);

		User currentLoggedInUser = commonService.getCurrentUserDetails();

		return reviewAndCommentsService.getListOfAllReviewAndComments(reviewAndComments, pageNo, sortKey,
				recordsPerPage, sortDir, currentLoggedInUser);

	}

	@PreAuthorize("hasAnyAuthority('CREATE_ORG', 'SUPPER_ADMIN_API', 'ADMIN_API')")
	@PostMapping(path = { "/reviewAndComments" })
	public ReviewAndComments saveOrUpdateUser(@RequestBody ReviewAndComments reviewAndComments) {
		logger.info("Inside saveOrUpdateUser method in controller");

		User currentLoggedInUser = commonService.getCurrentUserDetails();
		String userName = commonService.getUserNameByLoggedInUser(currentLoggedInUser);

		reviewAndComments.setCreatedBy(userName);
		if (reviewAndComments.getId() > 0) {
			reviewAndComments.setUpdatedBy(userName);
		} else {
			reviewAndComments.setUpdatedBy(userName);
		}

		return reviewAndCommentsService.saveOrUpdateReviewAndComments(reviewAndComments);
	}

	@PreAuthorize("hasAnyAuthority('READ_ORG', 'COMMON_API')")
	@GetMapping(path = { "/reviewAndComments/{id}" })
	public ReviewAndComments loadOrgById(@PathVariable(required = false) String erpOrgCode, @PathVariable Integer id,
			@PathVariable(required = false) String erpEntityCode) {
		logger.info("Inside loadOrgById method in controller");

		User currentLoggedInUser = commonService.getCurrentUserDetails();

		ReviewAndComments reviewAndComments = reviewAndCommentsService.loadReviewAndCommentsById(id,
				currentLoggedInUser);

		if (reviewAndComments == null) {
			String recordNotFoundMessage = "ReviewAndComments not found for ReviewAndComments Id - " + id;
			logger.info(recordNotFoundMessage);
			throw new RecordNotFoundException(recordNotFoundMessage);
		}

		return reviewAndComments;
	}

	@PreAuthorize("hasAnyAuthority('DELETE_ORG', 'SUPPER_ADMIN_API', 'ADMIN_API')")
	@DeleteMapping(path = { "/reviewAndComments/{id}" })
	public void deleteOrgById(@PathVariable(required = false) String erpOrgCode, @PathVariable Integer id) {
		logger.info("Inside deleteOrgById method in controller");

		User currentLoggedInUser = commonService.getCurrentUserDetails();
		reviewAndCommentsService.deleteReviewAndCommentsById(id, currentLoggedInUser);
	}

}
