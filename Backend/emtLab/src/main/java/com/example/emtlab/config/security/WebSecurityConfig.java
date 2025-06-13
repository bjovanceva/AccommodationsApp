package com.example.emtlab.config.security;

import com.example.emtlab.security.CustomUsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    public WebSecurityConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

//  CORS (Cross-Origin Resource Sharing) е безбедносен механизам што контролира
//  како веб-страниците можат да прават HTTP барања до други домени (Cross-Origin).
//  Пример: Ако твојот frontend (React) работи на http://localhost:3000,
//  а backend (Spring Boot) на http://localhost:8080, CORS мора да биде дозволен за тие да комуницираат.
//  CORS дозволува комуникација меѓу frontend и backend

//    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

//    CSRF е напад што го искористува автоматското праќање на cookies при HTTP барања.
//    Во REST API, аутентификацијата се прави со JWT, кој не се праќа автоматски од браузерот.
//    Затоа CSRF е оневозможен (http.csrf().disable()).

//    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                        corsConfigurationSource()));  //со ова е признаен првиот bean
//                .authorizeHttpRequests(requests -> requests.requestMatchers(
//                        "/api/accommodations",
//                        "/api/countries",
//                        "/api/hosts",
//                        "api/user/login",
//                        "api/user/register"
//                ).permitAll().anyRequest().hasRole("USER"))
//                .formLogin((form) -> form.loginProcessingUrl(
//                                "/api/user/login")
//                        .permitAll()
//                        .failureUrl("/api/user/login?error=BadCredentials")
//                        .defaultSuccessUrl(
//                                "/swagger-ui/index.html",
//                                true
//                        ))
//                .logout((logout) -> logout.logoutUrl("/api/user/logout")
//                        .clearAuthentication(true)
//                        .invalidateHttpSession(
//                                true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/api/user/login"))
//                .exceptionHandling((ex) -> ex.accessDeniedPage(
//                        "/access_denied"));
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        return authenticationManagerBuilder.build();
    }
}
