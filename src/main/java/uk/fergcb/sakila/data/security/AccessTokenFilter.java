package uk.fergcb.sakila.data.security;

import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.springframework.util.StringUtils.hasText;

@Component
public class AccessTokenFilter extends OncePerRequestFilter {

    private static final String PREFIX = "Bearer ";

    private final RestTemplate restTemplate;

    public AccessTokenFilter() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        // If the request is already authenticated, continue
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            chain.doFilter(req, res);
            return;
        }

        // Validate Authorization header
        final String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (!hasText(header) || !header.startsWith(PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        // Extract access token from Authorization header
        final String accessToken = header.substring(PREFIX.length()).trim();

        final User user = validateToken(accessToken);

        if (user == null) {
            chain.doFilter(req, res);
            return;
        }

        // Authenticate the request
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user,
                AuthorityUtils.createAuthorityList(user.getGroup())
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        chain.doFilter(req, res);
    }

    private User validateToken(String accessToken) {
        final String url = "https://sakila-auth.fergcb.uk/sessions/validate";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        final Map<String, String> body = Map.of(
                "accessToken", accessToken
        );

        final HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        final ResponseEntity<User> res = restTemplate.postForEntity(url, request, User.class);

        if (res.getStatusCode() == HttpStatus.OK) {
            return res.getBody();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
