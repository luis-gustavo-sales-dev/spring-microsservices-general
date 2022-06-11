package br.dev.luisgustavosales.generatepassword.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;
import br.dev.luisgustavosales.generatepassword.repositories.PasswordRepository;

@Service
public class PasswordService {
	
	@Autowired
	private PasswordRepository passwordRepository;
	
	public PasswordInfo findPasswordInfoById(Long id) {
		var passwordInfo = passwordRepository.findById(id);
		if (passwordInfo.isPresent()) {
			return passwordInfo.get();	
		}
		return null;
	}
	
	public PasswordInfo createPasswordInfo(
			PasswordInfo passwordInfo,
			Long userId) {
		var passwordInfoByUser = passwordRepository
				.findByNameAndUserId(passwordInfo.getName(), userId);
		
		if (passwordInfoByUser.isEmpty()) {
			var passwordInfoCreated = passwordRepository.save(passwordInfo);
			return passwordInfoCreated;
		}
		return null;
	}
}
