package org.arj.jaxrs.springboot.auth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.holonplatform.auth.Account;
import com.holonplatform.auth.Account.AccountProvider;
import com.holonplatform.auth.AuthenticationToken;
import com.holonplatform.auth.Realm;
import com.holonplatform.auth.jwt.JwtAuthenticator;
import com.holonplatform.auth.jwt.JwtConfiguration;

//@ApplicationPath("api")
//@OpenAPIDefinition(info =
//  @Info(title = "Sample Project",
//      version = "0.0.1",
//  description = "Demo API",
//      license = @License(name = "Apache 2.0", url = "http://foo.bar"),
//      contact = @Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
//  ),
////  tags = {
////    @Tag(name = "Administration", description = "Admin API", externalDocs = @ExternalDocumentation(description = "docs desc")),
////    @Tag(name = "User", description = "User API", externalDocs = @ExternalDocumentation(description = "docs desc 2"))
////  },
//  externalDocs = @ExternalDocumentation(description = "definition docs desc"),
//  security = {
//    @SecurityRequirement(name = "foo", scopes = {})
//  },
//  servers = {
//		    @Server(description = "local-http",
//                    url = "http://localhost:8080/"),
//    @Server(description = "local-https",
//                    url = "https://localhost:8080/")
//  }
//)
// https://howtodoinjava.com/resteasy/jax-rs-resteasy-basic-authentication-and-authorization-tutorial/
@SpringBootApplication
public class Application {

	@Bean
	public Realm realm(AccountProvider accountProvider, JwtConfiguration jwtConfiguration) {
		return Realm.builder()
	      .withResolver(AuthenticationToken.httpBasicResolver())
		  .withAuthenticator(Account.authenticator(accountProvider)).withDefaultAuthorizer()
		  .withResolver(AuthenticationToken.httpBearerResolver())
		  .withAuthenticator(JwtAuthenticator.builder().configuration(jwtConfiguration).issuer("jwt-issuer").build())
		  .withDefaultAuthorizer().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
