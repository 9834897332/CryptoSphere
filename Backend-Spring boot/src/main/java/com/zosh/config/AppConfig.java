package com.zosh.config;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.zosh.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class AppConfig {
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(Management -> Management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())
            .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

	       
		
	    // CORS Configuration
	    private CorsConfigurationSource corsConfigurationSource() {
	        return new CorsConfigurationSource() {
	            @Override
	            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	                CorsConfiguration cfg = new CorsConfiguration();
	                cfg.setAllowedOrigins(Arrays.asList(
	                    "http://localhost:3000",
	                    "http://localhost:5173",
						"http://localhost:5174",
	                    "http://localhost:4200",
						"https://cryptosphere-dun.vercel.app"
	                ));
	                cfg.setAllowedMethods(Collections.singletonList("*"));
	                cfg.setAllowCredentials(true);
	                cfg.setAllowedHeaders(Collections.singletonList("*"));
	                cfg.setExposedHeaders(Arrays.asList("Authorization"));
	                cfg.setMaxAge(3600L);
	                return cfg;
	            }
	        };
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}


}
