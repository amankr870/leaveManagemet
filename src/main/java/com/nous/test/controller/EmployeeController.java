/**
 * 
 */
package com.nous.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nous.test.auth.JWTAuthenticationEntryPoint;
import com.nous.test.auth.JWTTokenUtillity;
import com.nous.test.auth.req.JWTRequest;
import com.nous.test.auth.res.JWTResponse;
import com.nous.test.auth.service.IEmployeeService;
import com.nous.test.auth.service.JWTUserDetailsService;
import com.nous.test.dao.LeaveApplication;
import com.nous.test.dao.UserDTO;

import io.swagger.annotations.Api;

/**
 * @author Aman
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
@Api(value = "Leave admin api", description = "Leave admin")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService empService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenUtillity jwtTokenUtil;

	@Autowired
	private JWTUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {
		logger.info("In " + this.getClass().getSimpleName() + "createAuthenticationToken()");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JWTResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		logger.info("In " + this.getClass().getSimpleName() + "saveUser()");
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		logger.info("In " + this.getClass().getSimpleName() + "authenticate()");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PreAuthorize("hasRole('MANAGER')")
//	@Secured ({"ROLE_MANAGER"})
	@RequestMapping(value = "/approve", method = RequestMethod.PUT)
	public String approve(@RequestBody LeaveApplication leaveApplication) {
		logger.info("In " + this.getClass().getSimpleName() + "approve()");
		empService.approveLeave(leaveApplication);

		return null;
	}

	@PreAuthorize("hasRole('DEV')")
//	@Secured ({"ROLE_DEV"})
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public String applyLeave(@RequestBody LeaveApplication leaveApplication) {
		logger.info("In " + this.getClass().getSimpleName() + "applyLeave()");
		empService.applyLeave(leaveApplication);

		return null;
	}

}
