package br.dev.luisgustavosales.generatepassword.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.luisgustavosales.generatepassword.entities.PasswordLogin;

public interface PasswordLoginRepository extends JpaRepository<PasswordLogin, Long> {

}
