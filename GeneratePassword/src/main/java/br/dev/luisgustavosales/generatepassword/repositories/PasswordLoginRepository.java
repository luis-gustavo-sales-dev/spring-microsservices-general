package br.dev.luisgustavosales.generatepassword.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.luisgustavosales.generatepassword.entities.PasswordLogin;

public interface PasswordLoginRepository extends JpaRepository<PasswordLogin, Long> {
	
	Optional<PasswordLogin> findByNameAndUsername(String name, String username);
	Optional<PasswordLogin> findByIdAndUsername(Long id, String username);
	Optional<PasswordLogin> findByName(String name);

}
