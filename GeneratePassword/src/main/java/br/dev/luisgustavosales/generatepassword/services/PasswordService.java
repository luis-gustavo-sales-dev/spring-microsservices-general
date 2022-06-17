package br.dev.luisgustavosales.generatepassword.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;
import br.dev.luisgustavosales.generatepassword.repositories.PasswordInfoRepository;

@Service
public class PasswordService {
	
	@Autowired
	private PasswordInfoRepository passwordRepository;
	
	public PasswordInfo findPasswordInfoById(Long id) {
		var passwordInfo = passwordRepository.findById(id);
		if (passwordInfo.isPresent()) {
			return passwordInfo.get();	
		}
		return null;
	}
	
	public PasswordInfo createPasswordInfo(
			PasswordInfo passwordInfo,
			String username) {
		var passwordInfoByUser = passwordRepository
				.findByNameAndUsername(passwordInfo.getName(), username);
		
		if (passwordInfoByUser.isEmpty()) {
			var passwordInfoCreated = passwordRepository.save(passwordInfo);
			return passwordInfoCreated;
		}
		return null;
	}
}
