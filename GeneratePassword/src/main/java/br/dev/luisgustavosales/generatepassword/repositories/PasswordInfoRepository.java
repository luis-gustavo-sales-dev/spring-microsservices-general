package br.dev.luisgustavosales.generatepassword.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;

@Repository
public interface PasswordInfoRepository extends JpaRepository<PasswordInfo, Long>{
	Optional<PasswordInfo> findByNameAndUsername(String name, String username);
	Optional<PasswordInfo> findByName(String name);
}
