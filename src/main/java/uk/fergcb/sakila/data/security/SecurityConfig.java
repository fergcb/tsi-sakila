package uk.fergcb.sakila.data.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    private final AccessTokenFilter accessTokenFilter;

    public SecurityConfig(AccessTokenFilter accessTokenFilter) {
        this.accessTokenFilter = accessTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors() // Enable CORS
                .and()
                .csrf().disable() // Disable CSRF

                // Use stateless auth
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // Return 401 Unauthorized on auth rejection
                .exceptionHandling()
                .authenticationEntryPoint(
                        (req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage())
                )
                .and()

                // Select endpoints requiring auth
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll() // GET requests don't need authentication
                .anyRequest().hasAuthority("ADMIN") // All other endpoints/actions
                .and()

                // Add the JWT validator before username/password authentication
                .addFilterAfter(
                        accessTokenFilter,
                        BasicAuthenticationFilter.class
                )

                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://sakila.fergcb.uk");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
