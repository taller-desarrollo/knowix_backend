package bo.com.knowix.config;

import bo.com.knowix.bl.UserBl;
import bo.com.knowix.entity.KcUserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class BlockedUserFilter extends OncePerRequestFilter {

    private final UserBl userBl;
    private final KeycloakJwtTokenConverter keycloakJwtTokenConverter;


    public BlockedUserFilter(UserBl userBl, KeycloakJwtTokenConverter keycloakJwtTokenConverter) {
        this.userBl = userBl;
        this.keycloakJwtTokenConverter = keycloakJwtTokenConverter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwtToken = (Jwt) authentication.getPrincipal();
            String sub = jwtToken.getSubject();

            AbstractAuthenticationToken token = keycloakJwtTokenConverter.convert(jwtToken);
            Collection<GrantedAuthority> authorities = token.getAuthorities();
            if (authorities.contains(new SimpleGrantedAuthority("ROLE_administrator"))) {
                filterChain.doFilter(request, response);
                return;
            }
              KcUserEntity user = userBl.getUser(sub);
            if(user.isBlocked()){
                logger.warn("El usuario está bloqueado");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
