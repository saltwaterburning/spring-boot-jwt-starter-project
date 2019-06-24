package org.arj.jaxrs.springboot.auth.jwt;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.holonplatform.auth.Account;
import com.holonplatform.auth.AuthenticationToken;
import com.holonplatform.auth.Realm;
import com.holonplatform.auth.Account.AccountProvider;
import com.holonplatform.auth.jwt.JwtAuthenticator;
import com.holonplatform.auth.jwt.JwtConfiguration;

@Configuration
@EnableAutoConfiguration
public class AppConfig {
	@Bean
	public Realm realm(AccountProvider accountProvider, JwtConfiguration jwtConfiguration) {
		return Realm.builder()
	      .withResolver(AuthenticationToken.httpBasicResolver())
		  .withAuthenticator(Account.authenticator(accountProvider)).withDefaultAuthorizer()
		  .withResolver(AuthenticationToken.httpBearerResolver())
		  .withAuthenticator(JwtAuthenticator.builder().configuration(jwtConfiguration).issuer("jwt-issuer").build())
		  .withDefaultAuthorizer().build();
	}
}
