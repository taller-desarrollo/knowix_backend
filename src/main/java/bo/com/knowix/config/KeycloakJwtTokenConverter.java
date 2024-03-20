package bo.com.knowix.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeycloakJwtTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;
    private final TokenConverterProperties properties;

    private Logger logger = LoggerFactory.getLogger(KeycloakJwtTokenConverter.class);

    public KeycloakJwtTokenConverter(
            JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter,
            TokenConverterProperties properties) {
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
        this.properties = properties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        return properties.getPrincipalAttribute()
                .map(jwt::getClaimAsString)
                .orElse(jwt.getClaimAsString(JwtClaimNames.SUB));
    }

    public String getSubject(Jwt jwt) {
        return jwt.getClaimAsString(JwtClaimNames.SUB);
    }


    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resourceRoles = (Map<String, Object>) resourceAccess.get(properties.getResourceId());
        if (resourceRoles == null) {
            resourceRoles = (Map<String, Object>) resourceAccess.get("account");
            Collection<String> roles = (Collection<String>) resourceRoles.get("roles");
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toSet());
        }
        Collection<String> roles = (Collection<String>) resourceRoles.get("roles");
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}