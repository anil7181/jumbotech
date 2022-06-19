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

import com.tecart.pqp.entity.base.Organization;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.repository.OrgRepository;
import com.tecart.pqp.utils.constants.ClassPropertiesNameConstants;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@Service
public class OrgService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonService commonService;

	@Autowired
	private OrgRepository orgRepository;

	static Specification<Organization> containsOrganizationName(String name) {
		return (org, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(org.get(ClassPropertiesNameConstants.CLASS_PROPERTY_NAME)),
				"%" + name.toUpperCase() + "%");
	}

	static Specification<Organization> containsCode(String code) {
		return (org, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(org.get(ClassPropertiesNameConstants.CLASS_PROPERTY_CODE)),
				"%" + code.toUpperCase() + "%");
	}

	static Specification<Organization> orgOrganizationId(int id) {
		return (org, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(org.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ID), id);
	}

	private Specification<Organization> isOrganizationActive(int active) {
		return (org, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(org.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ACTIVE_FLAG), active);
	}

	public Page<Organization> getListOfAllOrganization(Organization org, Integer pageNo, String sortKey,
			Integer recordsPerPage, String sortDir, User loggedInUser) {
		logger.info("Inside getListOfAllOrganization method in service");

		Sort sort = null;
		if (sortDir.equals("desc")) {
			sort = Sort.by(Sort.Direction.DESC, sortKey);
		} else {
			sort = Sort.by(Sort.Direction.ASC, sortKey);
		}

		Pageable page = PageRequest.of(pageNo, recordsPerPage, sort);
		Page<Organization> orgPageableList = orgRepository.findAll(searchCriteriaForOrganization(org, loggedInUser),
				page);

		return orgPageableList;
	}

	private Specification<Organization> searchCriteriaForOrganization(Organization org, User currentLoggedInUser) {
		logger.info("Inside searchCriteriaForOrganization method in service");

		return Specification
				.where(commonService.isGivenStringNtEmptyAndNtNull(org.getName())
						? containsOrganizationName(org.getName())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(org.getCode())
						? containsCode(org.getCode())
						: null)
				.and(org.getId() > 0 ? orgOrganizationId(org.getId()) : null)
				.and(org.getActive() > 0 ? isOrganizationActive(org.getActive()) : null);
	}

	public Organization saveOrUpdateOrganization(Organization org) {
		logger.info("Inside saveOrUpdateOrganization method in service");

		Organization savedOrganization = orgRepository.saveAndFlush(org);

		return savedOrganization;
	}

	public Organization loadOrganizationById(Integer id, User loggedInUser) {
		logger.info("Inside loadOrganizationById method in service");
		Organization orgById = null;

		Optional<Organization> optionalOrganization = orgRepository
				.findOne(searchCriteriaForOrganization(new Organization(id), loggedInUser));

		if (optionalOrganization.isPresent()) {
			orgById = optionalOrganization.get();
		}

		return orgById;
	}

	public void deleteOrganizationById(Integer id, User loggedInUser) {
		logger.info("Inside deleteOrganizationById method in service");
		Organization orgToBeDeleted = loadOrganizationById(id, loggedInUser);

		if (orgToBeDeleted != null) {
			orgRepository.delete(orgToBeDeleted);
		} else {
			String recordNotFoundMessage = "Organization not found for Organization Id - " + id;
			logger.info(recordNotFoundMessage);
			throw new RecordNotFoundException(recordNotFoundMessage);
		}
	}

}
