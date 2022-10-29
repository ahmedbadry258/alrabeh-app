package com.rabeh.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration 
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		//http.cors().and().csrf().disable()  
		http
	        .authorizeRequests() 
	        .antMatchers("/","/home","/send","/login","/contacts/","/contacts/**").permitAll()
	        .antMatchers("/contacts").authenticated()
	        .antMatchers("/admin").hasAuthority("ADMIN")
	        .anyRequest().authenticated()
		  // Login Form Details
	        .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/perform_login")
            .failureUrl("/login?error=true")
            .permitAll()
            .defaultSuccessUrl("/contacts", true)
     // Logout Form Details
       .and()
       .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

     // Exception Details 
      .and() 
      .exceptionHandling()
     .accessDeniedPage("/accessDenied")
     
     .and().sessionManagement()
     .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
     ;
	      return http.build();
	}
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) ->web.ignoring().antMatchers("/css/**","/js/**","/img/**");
		
	}
}
