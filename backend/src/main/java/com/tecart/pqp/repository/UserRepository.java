package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecart.pqp.entity.base.User;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	
	User findUserByUserName(String userName);

	@Query(value = "SELECT * FROM base.user where user_name = :userName and active = true", nativeQuery = true)
	User findByUserByUserName(@Param("userName") String userName);
	
	@Query(value = "SELECT * FROM base.user where user_name = :userName and password = :password and active = 1;", nativeQuery = true)
	User findByUserNameAndPasswordWithValidToDateAfter(@Param("userName") String userName, @Param("password") String password);
		
	User findByUserName(String userName);
	
	@Query("SELECT u.password FROM User u where u.id = :userId")
	String findPasswordById(@Param("userId") int userId);
	
	@Query(value = "select * from base.user where LOWER(email_id) LIKE LOWER(:emailId)", nativeQuery = true)
	User findUserByEmailId(@Param("emailId")String emailId);
	
}
