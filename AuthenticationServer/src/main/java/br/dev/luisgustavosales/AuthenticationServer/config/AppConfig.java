package br.dev.luisgustavosales.AuthenticationServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//@SuppressWarnings("deprecation")
	//@Bean
	//public LdapShaPasswordEncoder ldapShaPasswordEncoder() {
	//	return new LdapShaPasswordEncoder();
	//}
}
