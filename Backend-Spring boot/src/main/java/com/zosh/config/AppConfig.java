package com.zosh.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.zosh.model.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            )
            .oauth2Login(oauth -> {
                oauth.loginPage("/login/google");
                oauth.authorizationEndpoint(authorization -> 
                    authorization.baseUri("/login/oauth2/authorization"));
                oauth.successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {

                        if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
                            DefaultOAuth2User userDetails = (DefaultOAuth2User) authentication.getPrincipal();
                            String email = userDetails.getAttribute("email");
                            String fullName = userDetails.getAttribute("name");
                            String phone = userDetails.getAttribute("phone");
                            String picture = userDetails.getAttribute("picture");
                            boolean emailVerified = Boolean.TRUE.equals(userDetails.getAttribute("email_verified"));

                            User user = new User();
                            user.setVerified(emailVerified);
                            user.setFullName(fullName);
                            user.setEmail(email);
                            user.setMobile(phone);
                            user.setPicture(picture);

                            System.out.println("--------------- " + email +
                                               "-------------" +
                                               "===========" +
                                               "-------" + user);
                        }

                    }
                });
            })
            .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    // CORS Configuration
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "http://localhost:5173",
            "http://localhost:5174",
            "http://localhost:4200",
            "https://cryptosphere-dun.vercel.app"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Filter customCorsFilter() {
        return new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletResponse res = (HttpServletResponse) response;
                HttpServletRequest req = (HttpServletRequest) request;

                res.setHeader("Access-Control-Allow-Origin", "*");
                res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
                res.setHeader("Access-Control-Expose-Headers", "Authorization");

                if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
                    res.setStatus(HttpServletResponse.SC_OK);
                } else {
                    chain.doFilter(request, response);
                }
            }

            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                // Not needed
            }

            @Override
            public void destroy() {
                // Not needed
            }
        };
    }
}
