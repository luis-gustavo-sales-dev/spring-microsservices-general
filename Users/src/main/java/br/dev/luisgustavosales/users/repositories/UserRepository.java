package br.dev.luisgustavosales.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.luisgustavosales.users.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

}
