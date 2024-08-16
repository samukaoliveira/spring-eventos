package com.eventoapp.eventoapp.config;

import com.eventoapp.eventoapp.models.User;
import com.eventoapp.eventoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/logar/**", "/public/**", "/css/**", "/js/**", "/images/**").permitAll()  // Permitir acesso às páginas públicas e recursos estáticos
                        .anyRequest().authenticated()  // Requer autenticação para qualquer outra requisição
                )
                .formLogin(form -> form
                        .loginPage("/logar")
                        .loginProcessingUrl("/login") // URL para onde o formulário envia os dados para autenticação
                        .defaultSuccessUrl("/eventos", true)// Página de login customizada
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                )
                .csrf().disable(); // Desabilitar CSRF temporariamente se estiver causando problemas

        return http.build();
    }


}
