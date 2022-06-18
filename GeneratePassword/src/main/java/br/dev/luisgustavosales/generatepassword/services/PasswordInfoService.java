package br.dev.luisgustavosales.generatepassword.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;
import br.dev.luisgustavosales.generatepassword.repositories.PasswordInfoRepository;

@Service
public class PasswordInfoService {

	@Autowired
	private PasswordInfoRepository passwordRepository;

	public PasswordInfo findPasswordInfoById(Long id) {
		var passwordInfo = passwordRepository.findById(id);
		if (passwordInfo.isPresent()) {
			return passwordInfo.get();
		}
		return null;
	}

	public List<PasswordInfo> findByNameContainingAndUsername(String name, String username) {

		var pg = passwordRepository.findByNameContainingIgnoreCaseAndUsername(name, username);

		if (pg.isPresent()) {
			return pg.get();
		}
		return null;
	}
	
	public PasswordInfo findByNameAndUsername(String name, String username) {

		var pg = passwordRepository.findByNameAndUsername(name, username);

		if (pg.isPresent()) {
			return pg.get();
		}
		return null;
	}

	public PasswordInfo create(PasswordInfo passwordInfo) {
		return passwordRepository.save(passwordInfo);
		
	}

	public PasswordInfo findByLoginId(Long id) {
		// TODO Auto-generated method stub
		var pl = this.passwordRepository.findByLoginId(id);

		if (pl.isPresent()) {
			return pl.get();
		}
		return null;
	}

	public PasswordInfo findByGroupId(Long id) {
		// TODO Auto-generated method stub
		var pl = this.passwordRepository.findByGroupId(id);

		if (pl.isPresent()) {
			return pl.get();
		}
		return null;
	}

}
