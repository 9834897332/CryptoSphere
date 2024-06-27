import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CorsConfig {

    @GetMapping("/cors-config")
    public CorsConfiguration getCorsConfig(HttpServletRequest request) {
        CorsConfigurationSource source = new AppConfig().corsConfigurationSource();
        return source.getCorsConfiguration(request);
    }
}
