package br.dev.luisgustavosales.generatepassword.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.luisgustavosales.generatepassword.entities.PasswordGroup;

public interface PasswordGroupRepository extends JpaRepository<PasswordGroup, Long>{

	Optional<PasswordGroup> findByNameAndUsername(String name, String username);
	Optional<PasswordGroup> findByIdAndUsername(Long id, String username);
	Optional<PasswordGroup> findByName(String name);
}
