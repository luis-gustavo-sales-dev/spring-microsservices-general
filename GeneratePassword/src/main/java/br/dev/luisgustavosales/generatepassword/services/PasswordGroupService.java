package br.dev.luisgustavosales.generatepassword.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.luisgustavosales.generatepassword.entities.PasswordGroup;
import br.dev.luisgustavosales.generatepassword.repositories.PasswordGroupRepository;

@Service
public class PasswordGroupService {
	@Autowired
	private PasswordGroupRepository passwordRepository;
	
	public PasswordGroup findById(Long id) {
		var passwordGroup = passwordRepository.findById(id);
		if (passwordGroup.isPresent()) {
			return passwordGroup.get();
		}
		return null;
	}
	
	public PasswordGroup findByIdAndUsername(
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
	
	public PasswordGroup findByNameAndUsername(
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
	
	public List<PasswordGroup> findByNameContainingAndUsername(
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
	
	public PasswordGroup findByName(
			String name) {
		
		var pg = passwordRepository.findByName(
				name);
		
		if (pg.isPresent()) {
			return pg.get();
		}
		return null;
	}
	
	public PasswordGroup create(
			PasswordGroup passwordGroup) {

		return  passwordRepository.save(passwordGroup);

	}
	
	public PasswordGroup update(
			PasswordGroup passwordGroup) {
		return passwordRepository.save(passwordGroup);

	}
	
	
	public void deleteById(Long id) {
		passwordRepository.deleteById(id);
	}
}
