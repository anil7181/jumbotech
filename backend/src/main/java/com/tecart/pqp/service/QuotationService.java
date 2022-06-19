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
import com.tecart.pqp.entity.product.Quotation;
import com.tecart.pqp.repository.QuotationRepo;
import com.tecart.pqp.utils.constants.ClassPropertiesNameConstants;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@Service
public class QuotationService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonService commonService;

	@Autowired
	private QuotationRepo quotationRepository;

	static Specification<Quotation> containsQuotationFirstName(String firstName) {
		return (quotation, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(quotation.get(ClassPropertiesNameConstants.CLASS_PROPERTY_QUOTATION_FIST_NAME)),
				"%" + firstName.toUpperCase() + "%");
	}

	static Specification<Quotation> containsQuotationLastName(String lastName) {
		return (quotation, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(quotation.get(ClassPropertiesNameConstants.CLASS_PROPERTY_QUOTATION_LAST_NAME)),
				"%" + lastName.toUpperCase() + "%");
	}

	static Specification<Quotation> containsEmailId(String emailId) {
		return (quotation, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(quotation.get(ClassPropertiesNameConstants.CLASS_PROPERTY_USER_EMAIL_ID)),
				"%" + emailId.toUpperCase() + "%");
	}

	static Specification<Quotation> containsCompanyName(String companyName) {
		return (quotation, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder
						.upper(quotation.get(ClassPropertiesNameConstants.CLASS_PROPERTY_QUOTATION_COMPANY_NAME)),
				"%" + companyName.toUpperCase() + "%");
	}

	static Specification<Quotation> containsCompanyType(String companyType) {
		return (quotation, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder
						.upper(quotation.get(ClassPropertiesNameConstants.CLASS_PROPERTY_QUOTATION_COMPANY_TYPE)),
				"%" + companyType.toUpperCase() + "%");
	}

	static Specification<Quotation> quotationQuotationId(int id) {
		return (quotation, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(quotation.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ID), id);
	}

	private Specification<Quotation> isQuotationActive(int active) {
		return (quotation, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(quotation.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ACTIVE_FLAG), active);
	}

	static Specification<Quotation> quotationHasProductId(int productId) {
		return (quotation, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(quotation.get(ClassPropertiesNameConstants.CLASS_PROPERTY_PRODUCT)
						.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ID), productId);
	}

	public Page<Quotation> getListOfAllQuotation(Quotation quotation, Integer pageNo, String sortKey,
			Integer recordsPerPage, String sortDir, User loggedInUser) {
		logger.info("Inside getListOfAllQuotation method in service");

		Sort sort = null;
		if (sortDir.equals("desc")) {
			sort = Sort.by(Sort.Direction.DESC, sortKey);
		} else {
			sort = Sort.by(Sort.Direction.ASC, sortKey);
		}

		Pageable page = PageRequest.of(pageNo, recordsPerPage, sort);
		Page<Quotation> quotationPageableList = quotationRepository
				.findAll(searchCriteriaForQuotation(quotation, loggedInUser), page);

		return quotationPageableList;
	}

	private Specification<Quotation> searchCriteriaForQuotation(Quotation quotation, User currentLoggedInUser) {
		logger.info("Inside searchCriteriaForQuotation method in service");

		return Specification
				.where(commonService.isGivenStringNtEmptyAndNtNull(quotation.getFirstName())
						? containsQuotationFirstName(quotation.getFirstName())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(quotation.getLastName())
						? containsQuotationFirstName(quotation.getLastName())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(quotation.getEmailId())
						? containsEmailId(quotation.getEmailId())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(quotation.getCompanyName())
						? containsCompanyName(quotation.getCompanyName())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(quotation.getCompanyType())
						? containsCompanyType(quotation.getCompanyType())
						: null)
				.and(quotation.getId() > 0 ? quotationQuotationId(quotation.getId()) : null)
				.and((quotation.getProduct() != null) && quotation.getProduct().getId() > 0
						? quotationQuotationId(quotation.getProduct().getId())
						: null)
				.and(quotation.getActive() > 0 ? isQuotationActive(quotation.getActive()) : null);
	}

	public Quotation saveOrUpdateQuotation(Quotation quotation) {
		logger.info("Inside saveOrUpdateQuotation method in service");

		Quotation savedQuotation = quotationRepository.saveAndFlush(quotation);

		return savedQuotation;
	}

	public Quotation loadQuotationById(Integer id, User loggedInUser) {
		logger.info("Inside loadQuotationById method in service");
		Quotation quotationById = null;

		Optional<Quotation> optionalQuotation = quotationRepository
				.findOne(searchCriteriaForQuotation(new Quotation(id), loggedInUser));

		if (optionalQuotation.isPresent()) {
			quotationById = optionalQuotation.get();
		}

		return quotationById;
	}

	public void deleteQuotationById(Integer id, User loggedInUser) {
		logger.info("Inside deleteQuotationById method in service");
		Quotation quotationToBeDeleted = loadQuotationById(id, loggedInUser);

		if (quotationToBeDeleted != null) {
			quotationRepository.delete(quotationToBeDeleted);
		} else {
			String recordNotFoundMessage = "Quotation not found for Quotation Id - " + id;
			logger.info(recordNotFoundMessage);
			throw new RecordNotFoundException(recordNotFoundMessage);
		}
	}
}
