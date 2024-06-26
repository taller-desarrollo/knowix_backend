package bo.com.knowix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bdd/qr/**").addResourceLocations("file:./bdd/qr/");

        registry.addResourceHandler("/bdd/images/**").addResourceLocations("file:./bdd/images/");

        registry.addResourceHandler("/bdd/comprobant/**").addResourceLocations("file:./bdd/comprobant/");


    }
}
