package plazoleta.config.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import plazoleta.adapters.driving.http.JwtService.JwtTokenValidationFilter;
import plazoleta.adapters.driving.http.JwtService.JwtTokenValidator;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenValidator tokenValidator;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(HttpMethod.POST, "/plate/**").hasAuthority("propietario")
                        .requestMatchers(HttpMethod.POST, "/restaurant/create").hasAuthority("admin")
                        .requestMatchers(HttpMethod.GET,"/restaurant/getAll").hasAuthority("cliente")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtTokenValidationFilter(tokenValidator), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
