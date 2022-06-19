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
import com.tecart.pqp.entity.product.Product;
import com.tecart.pqp.repository.ProductRepository;
import com.tecart.pqp.utils.constants.ClassPropertiesNameConstants;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@Service
public class ProductService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonService commonService;

	@Autowired
	private ProductRepository productRepository;

	static Specification<Product> containsProductName(String name) {
		return (product, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(product.get(ClassPropertiesNameConstants.CLASS_PROPERTY_NAME)),
				"%" + name.toUpperCase() + "%");
	}

	static Specification<Product> containsCode(String code) {
		return (product, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(product.get(ClassPropertiesNameConstants.CLASS_PROPERTY_CODE)),
				"%" + code.toUpperCase() + "%");
	}

	static Specification<Product> productProductId(int id) {
		return (product, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(product.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ID), id);
	}

	private Specification<Product> isProductActive(int active) {
		return (product, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(product.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ACTIVE_FLAG), active);
	}

	static Specification<Product> productHasCatId(int catId) {
		return (product, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(product.get(ClassPropertiesNameConstants.CLASS_PROPERTY_CATEGORY)
						.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ID), catId);
	}

	public Page<Product> getListOfAllProduct(Product product, Integer pageNo, String sortKey, Integer recordsPerPage,
			String sortDir, User loggedInUser) {
		logger.info("Inside getListOfAllProduct method in service");

		Sort sort = null;
		if (sortDir.equals("desc")) {
			sort = Sort.by(Sort.Direction.DESC, sortKey);
		} else {
			sort = Sort.by(Sort.Direction.ASC, sortKey);
		}

		Pageable page = PageRequest.of(pageNo, recordsPerPage, sort);
		Page<Product> productPageableList = productRepository.findAll(searchCriteriaForProduct(product, loggedInUser),
				page);

		return productPageableList;
	}

	private Specification<Product> searchCriteriaForProduct(Product product, User currentLoggedInUser) {
		logger.info("Inside searchCriteriaForProduct method in service");

		return Specification
				.where(commonService.isGivenStringNtEmptyAndNtNull(product.getName())
						? containsProductName(product.getName())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(product.getCode())
						? containsCode(product.getCode())
						: null)
				.and(product.getId() > 0 ? productProductId(product.getId()) : null)
				.and((product.getCategory() != null) && product.getCategory().getId() > 0
						? productProductId(product.getCategory().getId())
						: null)
				.and(product.getActive() > 0 ? isProductActive(product.getActive()) : null);
	}

	public Product saveOrUpdateProduct(Product product) {
		logger.info("Inside saveOrUpdateProduct method in service");

		Product savedProduct = productRepository.saveAndFlush(product);

		return savedProduct;
	}

	public Product loadProductById(Integer id, User loggedInUser) {
		logger.info("Inside loadProductById method in service");
		Product productById = null;

		Optional<Product> optionalProduct = productRepository
				.findOne(searchCriteriaForProduct(new Product(id), loggedInUser));

		if (optionalProduct.isPresent()) {
			productById = optionalProduct.get();
		}

		return productById;
	}

	public void deleteProductById(Integer id, User loggedInUser) {
		logger.info("Inside deleteProductById method in service");
		Product productToBeDeleted = loadProductById(id, loggedInUser);

		if (productToBeDeleted != null) {
			productRepository.delete(productToBeDeleted);
		} else {
			String recordNotFoundMessage = "Product not found for Product Id - " + id;
			logger.info(recordNotFoundMessage);
			throw new RecordNotFoundException(recordNotFoundMessage);
		}
	}
}
