package br.dev.luisgustavosales.AuthenticationServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@SuppressWarnings({ "deprecation", "unused" })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	// private LdapShaPasswordEncoder passwordEncoder;
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
	      .ldapAuthentication()
	        .userDnPatterns("uid={0},ou=idiff")
	        .groupSearchBase("ou=groups")
	        .contextSource()
	          .url("ldap://10.18.0.10/dc=centro,dc=iffluminense,dc=edu,dc=br")
	          .and()
	        .passwordCompare()
	          .passwordEncoder(passwordEncoder)
	          .passwordAttribute("givenName");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	      .authorizeRequests()
	        .anyRequest().fullyAuthenticated()
	        .and()
	      .formLogin();
	}
	
	

}
