package com.khun.studentregistration.config;


import com.khun.studentregistration.handler.LoginSuccessHandler;
import com.khun.studentregistration.repository.RoleRepository;
import com.khun.studentregistration.repository.UserRepository;
import com.khun.studentregistration.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http    .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers(
                                        "/resources/**",
                                        "/js/**",
                                        "/css/**",
                                        "/images/**",
                                        "/vendor/**",
                                        "/fragments/**"
                                ).permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()

                ).exceptionHandling(
                        (exceptionHandling) -> exceptionHandling
                                .accessDeniedPage("/accessDenied")
                )
                .formLogin(
                        (formLogin) -> formLogin.loginPage("/login")
                                .loginProcessingUrl("/process-login")
                                .successHandler(loginSuccessHandler)
                                .permitAll()
                )
                .logout(
                        (logout) -> logout.logoutUrl("/logout").permitAll()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl(userRepository, roleRepository);
    }


}
