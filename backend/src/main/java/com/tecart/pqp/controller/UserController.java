package com.tecart.pqp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecart.pqp.utils.constants.MasterConstants;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = MasterConstants.ROOT_API_PATH)
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
