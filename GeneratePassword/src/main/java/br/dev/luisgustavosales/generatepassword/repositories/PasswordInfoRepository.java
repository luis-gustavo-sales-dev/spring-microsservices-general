package br.dev.luisgustavosales.generatepassword.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.luisgustavosales.generatepassword.entities.PasswordInfo;

@Repository
public interface PasswordInfoRepository extends JpaRepository<PasswordInfo, Long>{
	Optional<PasswordInfo> findByNameAndUsername(String name, String username);
	Optional<PasswordInfo> findByName(String name);
	Optional<PasswordInfo> findByGroupId(Long idPasswordGroup);
	Optional<PasswordInfo> findByLoginId(Long idLoginGroup);
	Optional<List<PasswordInfo>> findByNameContainingIgnoreCaseAndUsername(String name, String username);
}
