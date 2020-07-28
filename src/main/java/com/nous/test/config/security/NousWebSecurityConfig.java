/**
 * 
 */
package com.nous.test.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nous.test.auth.JWTAuthenticationEntryPoint;
import com.nous.test.auth.req.JWTRequestFilter;

/**
 * @author hariprakash
 *
 */

@Configuration
@EnableWebSecurity
public class NousWebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired

	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired

	private UserDetailsService jwtUserDetailsService;

	@Autowired

	private JWTRequestFilter jwtRequestFilter;

	@Autowired

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	// configure AuthenticationManager so that it knows from where to load

	// user for matching credentials

	// Use BCryptPasswordEncoder

	auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());

	}

	@Bean

	public PasswordEncoder passwordEncoder() {

	return new BCryptPasswordEncoder();

	}

	@Bean

	@Override

	public AuthenticationManager authenticationManagerBean() throws Exception {

	return super.authenticationManagerBean();

	}

	@Override

	protected void configure(HttpSecurity httpSecurity) throws Exception {

	// We don't need CSRF for this example
		
	httpSecurity.cors().and().csrf().disable().

	// dont authenticate this particular request
	
	exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()

	.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

    .authorizeRequests()
    //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
    .antMatchers(HttpMethod.POST, "/leave").hasRole("MANAGER")
    .antMatchers(HttpMethod.PUT, "/leave").hasRole("DEV")
    // allow anonymous resource requests
    .antMatchers(
            HttpMethod.GET,
            "/",
            "/v2/api-docs",           // swagger
            "/webjars/**",            // swagger-ui webjars
            "/swagger-resources/**",  // swagger-ui resources
            "/configuration/**",      // swagger configuration
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"
    ).permitAll()
    .antMatchers("/api/**").permitAll()
    .anyRequest().authenticated();

	// Add a filter to validate the tokens with every request

	httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	

}
