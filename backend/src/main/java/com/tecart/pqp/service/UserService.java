package com.tecart.pqp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tecart.pqp.common.MyUserPrincipal;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.repository.UserRepository;
import com.tecart.pqp.utils.constants.ClassPropertiesNameConstants;
import com.tecart.pqp.utils.constants.MasterConstants;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@Service
public class UserService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommonService commonService;

	static Specification<User> containsUserName(String userName) {
		return (user, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(user.get(ClassPropertiesNameConstants.CLASS_PROPERTY_USER_NAME)),
				"%" + userName.toUpperCase() + "%");
	}

	static Specification<User> containsEmailId(String emailId) {
		return (user, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(user.get(ClassPropertiesNameConstants.CLASS_PROPERTY_USER_EMAIL_ID)),
				"%" + emailId.toUpperCase() + "%");
	}

	static Specification<User> containsMobileNo(String mobileNo) {
		return (user, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
				criteriaBuilder.upper(user.get(ClassPropertiesNameConstants.CLASS_PROPERTY_USER_MOBILE_NUMBER)),
				"%" + mobileNo.toUpperCase() + "%");
	}

	static Specification<User> userUserId(int userId) {
		return (user, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(user.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ID), userId);
	}

	static Specification<User> userHasOrgId(int orgId) {
		return (user, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(user.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ORG)
						.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ORG_ID), orgId);
	}

	static Specification<User> userHasBranchCode(String branchCode) {
		return (user, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.like(user.get(ClassPropertiesNameConstants.CLASS_PROPERTY_BRANCH)
						.get(ClassPropertiesNameConstants.CLASS_PROPERTY_BRANCH_CODE), "%" + branchCode + "%");
	}

	private Specification<User> isUserActive(int active) {
		return (user, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.equal(user.get(ClassPropertiesNameConstants.CLASS_PROPERTY_ACTIVE_FLAG), active);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Inside the loadUserByUsername method in service");

		User user = userRepository.findUserByUserName(username);

		if (user == null)
			new UsernameNotFoundException("Not Found: " + username);

		return new MyUserPrincipal(user, getAuthorities(user.getUserType()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(int userType) {
		logger.info("Inside the getAuthorities method in service");

		return getGrantedAuthorities(getPrivileges(userType));
	}

	private List<String> getPrivileges(int userType) {
		logger.info("Inside the getPrivileges method in service");

		List<String> privileges = new ArrayList<>();
		if (userType == MasterConstants.DEFAULT_INT_SUPER_ADMIN) {
			privileges.add(MasterConstants.SUPPER_ADMIN_API);
		} else if (userType == MasterConstants.DEFAULT_INT_ADMIN) {
			privileges.add(MasterConstants.ADMIN_API);
		} else if (userType == MasterConstants.DEFAULT_INT_NORMAL_USER) {
			privileges.add(MasterConstants.NORMAL_API);
		}
		privileges.add(MasterConstants.COMMON_API);
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		logger.info("Inside getPrivileges method in service");

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	public UserDetails loadUserByUsernameAndBranchId(String username, int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<User> getListOfAllUser(User user, Integer pageNo, String sortKey, Integer recordsPerPage,
			String sortDir, User loggedInUser) {
		logger.info("Inside getListOfAllUser method in service");

		Sort sort = null;
		if (sortDir.equals("desc")) {
			sort = Sort.by(Sort.Direction.DESC, sortKey);
		} else {
			sort = Sort.by(Sort.Direction.ASC, sortKey);
		}

		Pageable page = PageRequest.of(pageNo, recordsPerPage, sort);
		Page<User> userPageableList = userRepository.findAll(searchCriteriaForUser(user, loggedInUser), page);

		return userPageableList;
	}

	private Specification<User> searchCriteriaForUser(User user, User currentlyLoggedInUser) {
		logger.info("Inside searchCriteriaForUser method in service");

		return Specification
				.where(commonService.isGivenStringNtEmptyAndNtNull(user.getUserName())
						? containsUserName(user.getUserName())
						: null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(user.getEmailId()) ? containsEmailId(user.getEmailId())
						: null)
				.and(user.getId() > 0 ? userUserId(user.getId()) : null)
				.and(user.getActive() > 0 ? isUserActive(user.getActive()) : null)
				.and(commonService.isGivenStringNtEmptyAndNtNull(user.getMobileNo())
						? containsMobileNo(user.getMobileNo())
						: null);
	}

	public User saveOrUpdateUser(User user) {
		logger.info("Inside saveOrUpdateUser method in service");

		User savedUser = userRepository.saveAndFlush(user);

		return savedUser;
	}

	@SuppressWarnings("static-access")
	public User authenticateUser(User user) {
		logger.info("Inside authenticateUser method in service");

		User authenticatedUser = null;

		authenticatedUser = userRepository.findByUserNameAndPasswordWithValidToDateAfter(user.getUserName(),
				user.getPassword());

		return authenticatedUser;
	}

	public User findUserByUserName(String userName) {
		logger.info("Inside findUserBuLoginId method in service");

		return userRepository.findUserByUserName(userName);

	}

	public User loadUserById(Integer id, User loggedInUser) {
		logger.info("Inside loadUserById method in service");
		User userById = null;

		Optional<User> optionalUser = userRepository.findOne(searchCriteriaForUser(new User(id), loggedInUser));

		if (optionalUser.isPresent()) {
			userById = optionalUser.get();
		}

		return userById;
	}

	public void deleteUserById(Integer id, User loggedInUser) {
		logger.info("Inside deleteUserById method in service");
		User userToBeDeleted = loadUserById(id, loggedInUser);

		if (userToBeDeleted != null) {
			userRepository.delete(userToBeDeleted);
		} else {
			String recordNotFoundMessage = "User not found for User Id - " + id;
			logger.info(recordNotFoundMessage);
			throw new RecordNotFoundException(recordNotFoundMessage);
		}
	}
}
