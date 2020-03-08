package org.proyectosfgk.config;

import java.security.Key;

import org.proyectosfgk.service.JPAUSerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//Objeto para encryptar y desencriptar claves
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Secret para cifrar o descifrar token
	private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	@Autowired
	private JPAUSerDetails userDetailsServices;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/usuarios/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), SECRET_KEY))
		.addFilter(new JWTAuthorization(authenticationManager(), SECRET_KEY))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.addFilter(null)
//		.addFilter(null)
		;
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userDetailsServices)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
