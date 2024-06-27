package com.zosh.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class AppConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow access from these origins
        config.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "https://cryptosphere-dun.vercel.app"
        ));
        
        // Allow all methods (GET, POST, etc.)
        config.setAllowedMethods(Arrays.asList("*"));
        
        // Allow all headers
        config.setAllowedHeaders(Arrays.asList("*"));
        
        // Expose headers if needed
        config.setExposedHeaders(Arrays.asList("Authorization"));
        
        // Allow credentials
        config.setAllowCredentials(true);
        
        // Set max age for CORS preflight
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    // Other beans and configurations can be added as needed
    
}
