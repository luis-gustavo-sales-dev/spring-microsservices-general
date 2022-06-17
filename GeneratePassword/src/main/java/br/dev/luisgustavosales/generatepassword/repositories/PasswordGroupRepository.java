package br.dev.luisgustavosales.generatepassword.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.luisgustavosales.generatepassword.entities.PasswordGroup;

public interface PasswordGroupRepository extends JpaRepository<PasswordGroup, Long>{

}
