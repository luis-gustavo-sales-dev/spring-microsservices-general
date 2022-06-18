package br.dev.luisgustavosales.generatepassword.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.luisgustavosales.generatepassword.entities.PasswordLogin;
import br.dev.luisgustavosales.generatepassword.repositories.PasswordLoginRepository;

@Service
public class PasswordLoginService {
	@Autowired
	private PasswordLoginRepository passwordRepository;
	
	public PasswordLogin findById(Long id) {
		var passwordLogin = passwordRepository.findById(id);
		if (passwordLogin.isPresent()) {
			return passwordLogin.get();
		}
		return null;
	}
	
	public PasswordLogin findByIdAndUsername(
			Long id,
			String username) {
		
		var pg = passwordRepository.findByIdAndUsername(
				id,
				username);
		
		if (pg.isPresent()) {
			return pg.get();
		}
		return null;
	}
	
	public PasswordLogin findByNameAndUsername(
			String name,
			String username) {
		
		var pg = passwordRepository.findByNameAndUsername(
				name,
				username);
		
		if (pg.isPresent()) {
			return pg.get();
		}
		return null;
	}
	
	public List<PasswordLogin> findByNameContainingAndUsername(
			String name,
			String username) {
		
		var pg = passwordRepository.findByNameContainingIgnoreCaseAndUsername(
				name,
				username);
		
		if (pg.isPresent()) {
			return pg.get();
		}
		return null;
	}
	
	public PasswordLogin findByName(
			String name) {
		
		var pg = passwordRepository.findByName(
				name);
		
		if (pg.isPresent()) {
			return pg.get();
		}
		return null;
	}
	
	public PasswordLogin create(
			PasswordLogin passwordLogin) {

		return  passwordRepository.save(passwordLogin);

	}
	
	public PasswordLogin update(
			PasswordLogin passwordLogin) {
		return passwordRepository.save(passwordLogin);

	}
	
	
	public void deleteById(Long id) {
		passwordRepository.deleteById(id);
	}


}
