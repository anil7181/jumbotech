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
import com.tecart.pqp.entity.product.Product;
import com.tecart.pqp.service.ProductService;
import com.tecart.pqp.service.CommonService;
import com.tecart.pqp.utils.constants.MasterConstants;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = MasterConstants.ROOT_API_PATH)
public class ProductController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonService commonService;

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasAnyAuthority('READ_ORG', 'SUPPER_ADMIN_API', 'ADMIN_API', 'GUEST_ROLE')")
	@GetMapping({ "/products" })
	public Page<Product> getListOfAllUser(
			@RequestParam(value = "pageNo", defaultValue = MasterConstants.DEFAULT_PAGE_NO_VALUE) Integer pageNo,
			@RequestParam(value = "sortKey", defaultValue = MasterConstants.DEFAULT_SORT_KEY) String sortKey,
			@RequestParam(value = "recordsPerPage", defaultValue = MasterConstants.DEFAULT_RECORDS_PER_PAGE_VALUE) Integer recordsPerPage,
			@RequestParam(value = "sortDir", defaultValue = MasterConstants.DEFAULT_SORT_DIR) String sortDir,
			@RequestParam(value = "body") String productParam) throws JsonProcessingException {
		logger.info("Inside getListOfAllUser method in controller");

		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(productParam, Product.class);

		User currentLoggedInUser = commonService.getCurrentUserDetails();

		return productService.getListOfAllProduct(product, pageNo, sortKey, recordsPerPage, sortDir, currentLoggedInUser);

	}

	@PreAuthorize("hasAnyAuthority('CREATE_ORG', 'SUPPER_ADMIN_API', 'ADMIN_API')")
	@PostMapping(path = { "/products" })
	public Product saveOrUpdateUser(@RequestBody Product product) {
		logger.info("Inside saveOrUpdateUser method in controller");

		User currentLoggedInUser = commonService.getCurrentUserDetails();
		String userName = commonService.getUserNameByLoggedInUser(currentLoggedInUser);

		product.setCreatedBy(userName);
		if (product.getId() > 0) {
			product.setUpdatedBy(userName);
		} else {
			product.setUpdatedBy(userName);
		}

		return productService.saveOrUpdateProduct(product);
	}

	@PreAuthorize("hasAnyAuthority('READ_ORG', 'COMMON_API', 'GUEST_ROLE')")
	@GetMapping(path = { "/products/{id}" })
	public Product loadOrgById(@PathVariable(required = false) String erpOrgCode, @PathVariable Integer id,
			@PathVariable(required = false) String erpEntityCode) {
		logger.info("Inside loadOrgById method in controller");

		User currentLoggedInUser = commonService.getCurrentUserDetails();

		Product product = productService.loadProductById(id, currentLoggedInUser);

		if (product == null) {
			String recordNotFoundMessage = "Product not found for Product Id - " + id;
			logger.info(recordNotFoundMessage);
			throw new RecordNotFoundException(recordNotFoundMessage);
		}

		return product;
	}

	@PreAuthorize("hasAnyAuthority('DELETE_ORG', 'SUPPER_ADMIN_API', 'ADMIN_API')")
	@DeleteMapping(path = { "/products/{id}" })
	public void deleteOrgById(@PathVariable(required = false) String erpOrgCode, @PathVariable Integer id) {
		logger.info("Inside deleteOrgById method in controller");

		User currentLoggedInUser = commonService.getCurrentUserDetails();
		productService.deleteProductById(id, currentLoggedInUser);
	}

}
