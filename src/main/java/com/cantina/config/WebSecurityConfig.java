package com.cantina.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cantina.security.jwt.AuthEntryPointJwt;
import com.cantina.security.jwt.AuthTokenFilter;
import com.cantina.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers("/api/foods/addFoodProduct").hasRole("ADMIN")
			.antMatchers("/api/foods/updateFoodProduct").hasRole("ADMIN")
			.antMatchers("/api/foods/deleteFoodProduct").hasRole("ADMIN")
			.antMatchers("/api/foods/getFoodProduct").hasAnyRole("ADMIN", "USER")
            .antMatchers("/api/cantinaCart/**").hasRole("USER")
            .antMatchers("/api/checkout/**").hasRole("USER")
            .antMatchers("/api/updateUserInformation").hasRole("USER")
            .antMatchers("/api/getUserDetails").hasRole("USER")
            .antMatchers(PUBLIC_MATCHERS)
            .permitAll();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/images/**",
            "/api/signup",
            "/api/signin",
            "/api/forgetPassword",
			"/api/foods/foodList"
    };
}
