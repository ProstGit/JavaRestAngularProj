package com.social.controller;

import com.social.entities.User;
import com.social.services.UserService;
import com.social.util.OnError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLOutput;

@RestController
@RequestMapping("account")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

	// create a new account
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		if (userService.find(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity(
					new OnError("user with username " + newUser.getUsername() + "already exist "),
					HttpStatus.CONFLICT);
		}
		newUser.setRole("USER");
		
		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
	}


	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}

	@CrossOrigin
	@RequestMapping(value = "/changePas", method = RequestMethod.POST)
	public ResponseEntity<?> editUserPas(@RequestBody User udatedUser) {
		System.out.println(udatedUser.getPassword()+" "+udatedUser.getPassword());
		return new ResponseEntity<User>(userService.updatePas(udatedUser), HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<?> editUser(@RequestBody User udatedUser) {
		System.out.println(udatedUser.getEmail()+" "+udatedUser.getPassword());
		return new ResponseEntity<User>(userService.updateAll(udatedUser), HttpStatus.CREATED);
	}



}
