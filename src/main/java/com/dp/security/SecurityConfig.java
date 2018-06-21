package com.dp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private DataSource dataSource;
	
	/*@Autowired
	private AuthSucessHandler authHandler;*/
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCrypt);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		.antMatchers("/").permitAll()
        		.antMatchers("/login").permitAll()
        		.antMatchers("/register").permitAll()
        		.antMatchers("/current").permitAll()
        		.antMatchers("/dashboard/**").authenticated()
        	.and()
        	.formLogin()
        		.loginPage("/login")
        		.failureUrl("/login-error")
        		.defaultSuccessUrl("/dashboard")
        		.usernameParameter("userName")
        		.passwordParameter("password")
        		//.successHandler(authHandler)
        	.and()
        	.csrf().disable()
        	.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        	
        	
    }
}
