package org.rsol.poc.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user")
				.roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin")
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("super").password("super")
				.roles("SUPERADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/protected/**")
				.access("hasRole('ROLE_ADMIN')")
				.antMatchers("/confidential/**")
				.access("hasRole('ROLE_SUPERADMIN')").antMatchers("/session/**")
				.access("hasRole('ROLE_ADMIN')").and().formLogin();

	}

}
