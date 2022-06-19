package com.tecart.pqp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecart.pqp.common.JwtRequest;
import com.tecart.pqp.common.JwtResponse;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.service.UserService;
import com.tecart.pqp.utils.JwtTokenUtils;
import com.tecart.pqp.utils.constants.MasterConstants;

@RestController
@RequestMapping(path = MasterConstants.ROOT_API_PATH)
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtils jwtUtils;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(path = { "/authenticate/admin" })
	public ResponseEntity<?> createAdminAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		User authUser = new User();
		boolean isOrginalAndHashedPasswordSame = false;
		User adminUser = null;
		String token = null;
		User userFoundByUserName = userDetailsService.findUserByUserName(authenticationRequest.getUsername());
		authUser.setUserName(authenticationRequest.getUsername());

			if (passwordEncoder.matches(authenticationRequest.getPassword(), userFoundByUserName.getPassword())) {
				authUser.setPassword(userFoundByUserName.getPassword());
				adminUser = userDetailsService.authenticateUser(authUser);
				isOrginalAndHashedPasswordSame = true;
			}

		if (adminUser != null && isOrginalAndHashedPasswordSame) {
			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());
			Map<String, Object> claims = new HashMap<>();
			claims.put("dn", adminUser.getUserName());
//			claims.put("org", adminUser.getOrganization().getErpOrgCode());
//			claims.put("brn", adminUser.getEntity().getErpEntityCode());
			claims.put("uid", adminUser.getId());
			claims.put("acc", "admin");
//			claims.put("g2fa", adminUser.isUsing2FA());
//			claims.put("mfae", adminUser.isEnabled2fa());
			token = jwtUtils.generateTokenWithClaims(userDetails, claims);
			return ResponseEntity.ok(new JwtResponse(token));
		} else {
				throw new Exception("Invalid Credentials");
		}
	}

	@PostMapping(path = { "/validate/admin" })
	public ResponseEntity<?> validateAdminAuthenticationToken(@RequestHeader(value = "Authorization") String authToken)
			throws Exception {
		// ToDO: Relook this part, this may not be required.
		if (authToken != null && authToken.startsWith("Bearer ")) {

			return ResponseEntity.ok("Validation OK!");

		} else {
			throw new Exception("INVALID_TOKEN");
		}

	}

	@SuppressWarnings("unused")
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
