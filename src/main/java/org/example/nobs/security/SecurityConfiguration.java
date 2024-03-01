package org.example.nobs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("123"))
                .roles("BASIC","SPECIAL")
                .build();
        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("123"))
                .roles("BASIC")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
            return  httpSecurity
                    .authorizeRequests()
                    .requestMatchers(HttpMethod.GET,"special").hasRole("SPECIAL")
                    .requestMatchers(HttpMethod.GET,"basic").hasRole("SPECIAL,BASIC")
                    .and()
                    .formLogin(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults())
                    .build();
    }
}
