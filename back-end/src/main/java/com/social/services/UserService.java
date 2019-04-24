package com.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.dao.UserRepository;
import com.social.entities.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	public User updateAll(User user) {
		User u = userRepository.findByEmail(user.getEmail());
		user.setRole(u.getRole());
		u.updateUser(user);
		return userRepository.save(u);
	}

	private boolean checkPas(User user) {
		User u = userRepository.findByEmail(user.getEmail());
		return u.getPassword().equals(user.getPassword());
	}

	public User updatePas(User user){
		User u = userRepository.findByEmail(user.getEmail());
		if(checkPas(user)){
			u.setPassword(user.getPassword());
			userRepository.save(u);
		}
		return u;
	}

	public User find(String email) {
		return userRepository.findByEmail(email);
	}

	public User find(Long id) {
		return userRepository.findOne(id);
	}
}
