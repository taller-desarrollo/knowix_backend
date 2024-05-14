package bo.com.knowix.config;

import bo.com.knowix.bl.UserBl;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class GlobalSecurityConfiguration {
    private final KeycloakJwtTokenConverter keycloakJwtTokenConverter;

    private final Logger logger = LoggerFactory.getLogger(GlobalSecurityConfiguration.class);

    private final SecurityConstraintsProperties securityConstraintsProperties;
    private final UserBl userBl;
    public GlobalSecurityConfiguration(TokenConverterProperties properties, SecurityConstraintsProperties securityConstraintsProperties, UserBl userBL) {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter
                = new JwtGrantedAuthoritiesConverter();
        this.keycloakJwtTokenConverter
                = new KeycloakJwtTokenConverter(
                jwtGrantedAuthoritiesConverter,
                properties);
        this.securityConstraintsProperties = securityConstraintsProperties;
        this.userBl = userBL;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        List<SecurityConstraint> securityConstraints = securityConstraintsProperties.getConstraints();

        if (securityConstraints == null){
            logger.info("No security constraints found");
            return http.authorizeHttpRequests( (authorizeHttpRequests) -> {
                authorizeHttpRequests.anyRequest().permitAll();
            }).oauth2ResourceServer( (oauth2) -> {
                oauth2.jwt( (jwt) -> jwt.jwtAuthenticationConverter(keycloakJwtTokenConverter));
            }).sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
        }

        return http.authorizeHttpRequests( (authorizeHttpRequests) -> {
            securityConstraints.forEach( (constraint) -> {
                try {
                    List<String> authRoles = constraint.getAuthRoles();
                    List<SecurityCollection> securityCollections = constraint.getSecurityCollections();

                    securityCollections.forEach(collection -> {
                        String name = collection.getName();
                        List<String> patterns = collection.getPatterns();
                        List<String> methods = collection.getMethods();

                        List<HttpMethod> httpMethods = new ArrayList<>();

                        for (String method: methods
                        ) {
                            switch (method) {
                                case "GET":
                                    httpMethods.add(HttpMethod.GET);
                                    break;
                                case "POST":
                                    httpMethods.add(HttpMethod.POST);
                                    break;
                                case "PUT":
                                    httpMethods.add(HttpMethod.PUT);
                                    break;
                                case "DELETE":
                                    httpMethods.add(HttpMethod.DELETE);
                                    break;
                                case "PATCH":
                                    httpMethods.add(HttpMethod.PATCH);
                                    break;
                                default:
                                    break;
                            }
                        }

                        if (httpMethods.isEmpty()) {
                            if(authRoles.size() == 1){
                                String role = authRoles.get(0);
                                if(role.equals("permitAll")){
                                    logger.info("CONFIGURATION name: " + name + " patterns: " + patterns + " configuration: permitAll with no methods");
                                    authorizeHttpRequests
                                            .requestMatchers(patterns.toArray(new String[0]))
                                            .permitAll();
                                }else{
                                    logger.info("CONFIGURATION name: " + name + " patterns: " + patterns + " configuration: " + authRoles + " with no methods");
                                    authorizeHttpRequests
                                            .requestMatchers(patterns.toArray(new String[0]))
                                            .hasAnyRole(authRoles.toArray(new String[0]));
                                }
                            }else{
                                logger.info("CONFIGURATION name: " + name + " patterns: " + patterns + " configuration:" + authRoles + " with no methods");
                                authorizeHttpRequests
                                        .requestMatchers(patterns.toArray(new String[0]))
                                        .hasAnyRole(authRoles.toArray(new String[0]));
                            }
                        }else{
                            if (authRoles.size() == 1) {
                                String role = authRoles.get(0);
                                if (role.equals("permitAll")) {
                                    logger.info("CONFIGURATION name: " + name + " patterns: " + patterns + " configuration: permitAll with methods: " + httpMethods);
                                    for (HttpMethod httpMethod: httpMethods
                                    ) {
                                        authorizeHttpRequests
                                                .requestMatchers(httpMethod, patterns.toArray(new String[0]))
                                                .permitAll();
                                    }
                                }else{
                                    logger.info("CONFIGURATION name: " + name + " patterns: " + patterns + " configuration: " + authRoles + " with methods: " + httpMethods);
                                    for (HttpMethod httpMethod: httpMethods
                                    ) {
                                        authorizeHttpRequests
                                                .requestMatchers(httpMethod, patterns.toArray(new String[0]))
                                                .hasAnyRole(authRoles.toArray(new String[0]));
                                    }
                                }
                            }else{
                                logger.info("CONFIGURATION name: " + name + " patterns: " + patterns + " configuration:" + authRoles + "with methods: " + httpMethods);
                                for (HttpMethod httpMethod: httpMethods
                                ) {
                                    authorizeHttpRequests
                                            .requestMatchers(httpMethod, patterns.toArray(new String[0]))
                                            .hasAnyRole(authRoles.toArray(new String[0]));
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            authorizeHttpRequests.anyRequest().permitAll();
        })
                .oauth2ResourceServer( (oauth2) -> {oauth2.jwt( (jwt) -> jwt.jwtAuthenticationConverter(keycloakJwtTokenConverter));})
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf().disable()
                .addFilterAfter(new BlockedUserFilter(userBl, keycloakJwtTokenConverter), BasicAuthenticationFilter.class)
                .build();
    }

}
