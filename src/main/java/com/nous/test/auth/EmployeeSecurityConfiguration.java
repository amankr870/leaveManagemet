/**
 * 
 */
package com.nous.test.auth;

/*
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 */

/**
 * @author Aman
 *
 */

//@Configuration
//@EnableWebSecurity
//public class EmployeeSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	//@Autowired
   // DataSource dataSource;
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .csrf().disable() .authorizeRequests().anyRequest().authenticated() .and()
	 * .httpBasic(); }
	 * 
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.inMemoryAuthentication() .withUser("admin")
	 * .password("admin123") .roles("DEV"); }
	 */
    
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	//Enable jdbc authentication
	/*
	 * @Autowired public void configAuthentication(AuthenticationManagerBuilder
	 * auth) throws Exception { auth.jdbcAuthentication().dataSource(dataSource); }
	 * 
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().antMatchers("/api/**"); }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").
	 * hasAnyRole("DEV", "MANAGER") .antMatchers("/leave").hasAnyRole("DEV",
	 * "MANAGER").antMatchers("/approve")
	 * .hasAnyRole("MANAGER").anyRequest().authenticated().and().formLogin().
	 * loginPage("/login").permitAll() .and().logout().permitAll();
	 * 
	 * http.csrf().disable(); }
	 */
	

//}
