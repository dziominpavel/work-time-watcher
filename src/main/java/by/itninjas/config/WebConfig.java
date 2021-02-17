package by.itninjas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String[] allowedOriginsPath;

    public WebConfig(@Value("${cors-mapping.allowed-origins:}") String[] allowedOriginsPath) {
        this.allowedOriginsPath = allowedOriginsPath;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(allowedOriginsPath);
    }
}
