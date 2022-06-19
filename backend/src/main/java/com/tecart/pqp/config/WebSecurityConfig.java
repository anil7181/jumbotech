package com.tecart.pqp.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.techart.matrimony.service.UserService;
import com.techart.matrimony.utils.constants.MasterConstants;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors()
            .and()
        .csrf()
            .disable()
        .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        .anonymous().principal("guest").authorities("GUEST_ROLE")
        .and()
        .authorizeRequests()
        .antMatchers("/**/**")
                .permitAll()
		.antMatchers("/admin/autoLogin/**")
			.permitAll()
         .antMatchers(MasterConstants.ROOT_API_PATH + "/authenticate/**")
             .permitAll()
         .antMatchers(MasterConstants.ROOT_API_PATH + "/forgotPassword/**")
             .permitAll()
         .antMatchers(MasterConstants.ROOT_API_PATH + "/resetPassword/**")
             .permitAll()
        .anyRequest()
            .authenticated();
       
        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
       
    }
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	 @Bean
	 CorsConfigurationSource corsConfigurationSource() {
		 
	      CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*","http://localhost:3000/"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","OPTIONS","DELETE"));
	        configuration.setAllowCredentials(true);
	        //the below three lines will add the relevant CORS response headers
	        configuration.addAllowedOrigin("*");
	        configuration.addAllowedHeader("*");
	        configuration.addAllowedMethod("*");
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
	        return source;
	 }

}