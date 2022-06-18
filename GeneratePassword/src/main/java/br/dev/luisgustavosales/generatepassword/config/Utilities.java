package br.dev.luisgustavosales.generatepassword.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Utilities {

	public boolean canAcessPasswordResource(
			String username,
			String usernameHeader
			) {
		if (!username.equals(usernameHeader)) {
			//System.out.println("Username: " + username);
			//System.out.println("usernameHeader: " + usernameHeader);
			//System.out.println("return: " + !username.equals(usernameHeader));
			return false;
		}
		//System.out.println("Username: " + username);
		//System.out.println("usernameHeader: " + usernameHeader);
		//System.out.println("return: " + true);
		return true;
	}
}
