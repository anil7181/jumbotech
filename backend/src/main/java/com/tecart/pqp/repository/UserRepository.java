package com.tecart.pqp.repository;

import java.util.Date;

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
	User findByUserNameAndPasswordWithValidToDateAfter(@Param("userName") String userName, @Param("password") String password, @Param("today") Date today);
		
	User findByUserName(String userName);
	
}
