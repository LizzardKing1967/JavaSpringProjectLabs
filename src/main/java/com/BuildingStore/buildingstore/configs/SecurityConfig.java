package com.BuildingStore.buildingstore.configs;

import com.BuildingStore.buildingstore.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Profile("dev")
    @Bean
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login",  "/login?error=true", "/register", "/styles", "/h2-console/**").permitAll()
                                .requestMatchers(org.springframework.boot.autoconfigure.security.servlet.PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .anyRequest().authenticated()  // Все остальные запросы требуют авторизации
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Указываем путь к странице логина
                        .loginProcessingUrl("/login")  // Указывает, куда отправляется POST-запрос
                        .permitAll()  // Доступность страницы логина для всех
                        .failureUrl("/login?error=true") // Указываем, куда перенаправить при неудачной авторизации
                        .defaultSuccessUrl("/", true)  // Указываем страницу после успешного логина
                )


                .logout(logout -> logout.permitAll())  // Разрешаем выход для всех
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/register", "/")  // Откл  // Отключаем CSRF для H2
                )// Отключаем CSRF
                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives("frame-ancestors 'self'"))  // Разрешаем использование фреймов только с того же источника
                );

        return http.build();
    }

    @Profile("prod")
    @Bean
    public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login",  "/login?error=true", "/register", "/styles").permitAll()
                                .requestMatchers(org.springframework.boot.autoconfigure.security.servlet.PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .anyRequest().authenticated()  // Все остальные запросы требуют авторизации
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Указываем путь к странице логина
                        .loginProcessingUrl("/login")  // Указывает, куда отправляется POST-запрос
                        .permitAll()  // Доступность страницы логина для всех
                        .failureUrl("/login?error=true") // Указываем, куда перенаправить при неудачной авторизации
                        .defaultSuccessUrl("/", true)  // Указываем страницу после успешного логина
                )
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/register", "/", "/order-form/**", "/order-list/**")  // Откл  // Отключаем CSRF для H2
                );// Отключаем CSRF
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

