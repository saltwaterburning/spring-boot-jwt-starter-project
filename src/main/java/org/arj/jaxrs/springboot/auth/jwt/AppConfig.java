package org.arj.jaxrs.springboot.auth.jwt;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.holonplatform.auth.Account;
import com.holonplatform.auth.Account.AccountProvider;
import com.holonplatform.auth.AuthenticationToken;
import com.holonplatform.auth.Realm;
import com.holonplatform.auth.jwt.JwtAuthenticator;
import com.holonplatform.auth.jwt.JwtConfiguration;

@Configuration
@EnableAutoConfiguration
public class AppConfig {
//	@Bean
//    public WebMvcConfigurerAdapter webMvcConfigurerAdapter()
//    {
//        return new WebMvcConfigurerAdapter()
//        {
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry)
//            {
//                if (!registry.hasMappingForPattern("/**"))
//                {
//                    // if this is executed spring won't add default resource
//                    // locations - add them to the staticContentLocations if
//                    // you want to keep them
//                    // default locations:
//                    // WebMvcAutoConfiguration.RESOURCE_LOCATIONS
//                    registry.addResourceHandler("/**").addResourceLocations(
//                            staticContentLocations);
//                }
//            }
//        };
//    }
	
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
