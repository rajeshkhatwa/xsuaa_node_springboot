package com.rk.bulletin.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.Jwt;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;

@Configuration
@EnableWebSecurity
public class SecurityModule extends WebSecurityConfigurerAdapter {
  
	
   @Autowired
	XsuaaServiceConfiguration xsuaaServiceConfiguration;


	
	Converter<Jwt, AbstractAuthenticationToken> getJwtAuthoritiesConverter() {
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true);
        return converter;
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/students").hasAuthority("readDataKhatwa")
            .antMatchers("/students**").denyAll()
            .anyRequest().denyAll()
            .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(getJwtAuthoritiesConverter());
    }
}