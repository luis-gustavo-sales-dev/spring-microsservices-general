package br.dev.luisgustavosales.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.luisgustavosales.users.entities.User;
import br.dev.luisgustavosales.users.repositories.UserRepository;
import lombok.extern.java.Log;

@Service
@Log
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findUserById(Long id) {
		
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	
	}
	
	public User findUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		// log.info(user.toString());
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	@Transactional
	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User newUser = userRepository.save(user);
		return newUser;
	
	}

	@Transactional
	public User update(Long id, User user) {
		
		if (!userRepository.existsById(id)) {
			return null;
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
		
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
