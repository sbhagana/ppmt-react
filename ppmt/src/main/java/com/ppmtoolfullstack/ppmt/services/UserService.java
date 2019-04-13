package com.ppmtoolfullstack.ppmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ppmtoolfullstack.ppmt.Domain.User;
import com.ppmtoolfullstack.ppmt.exceptions.UsernameAlreadyExistsException;
import com.ppmtoolfullstack.ppmt.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User saveUSer(User newUser) {

		// Username should be unique

		// Make sure that password and confirm password match

		// We don't persist or show confirmPassword
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			newUser.setConfirmPassword("");
			return repository.save(newUser);
		} catch (Exception e) {
			throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
		}
	}
}
