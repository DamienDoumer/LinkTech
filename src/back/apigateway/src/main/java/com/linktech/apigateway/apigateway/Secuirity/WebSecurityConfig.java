package com.linktech.apigateway.apigateway.Secuirity;

import javax.servlet.http.HttpServletResponse;

import com.linktech.apigateway.apigateway.Models.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	// @Override
	// public void configure(final HttpSecurity http) throws Exception{
	// 	http.authorizeRequests()
	// 		.antMatchers("/eureka/**", "/api/authenticate", "/api/signup")
	// 		.permitAll()
	// 		.antMatchers("/**")
	// 		.authenticated();
	// }

	// @Override
	// protected void configure(HttpSecurity httpSecurity) throws Exception {
	// 	httpSecurity.csrf().disable()
	// 			.authorizeRequests()
	// 			.antMatchers("/api/authenticate", "/api/signup")
	// 			.permitAll().
	// 			anyRequest().authenticated().and().
	// 			exceptionHandling().and().sessionManagement()
    //             .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                
	// 	httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	// }
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .anonymous()
                .and()
                    .exceptionHandling().authenticationEntryPoint(
                            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                    .addFilterAfter(jwtRequestFilter,
                            UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
					.antMatchers("/authservice/**").permitAll()
					.antMatchers(HttpMethod.GET, "/usersservice/users").hasAuthority(Role.ADMIN_ROLE)
					.antMatchers("/usersservice/users/banUser").hasAuthority(Role.ADMIN_ROLE)
                    .anyRequest().authenticated();
    }
}