package com.dglee.apps.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://127.0.0.1:5500");
        configuration.addAllowedOrigin("http://localhost:5500");
        configuration.addAllowedOrigin("https://dglee.site");
        configuration.addAllowedOrigin("https://www.dglee.site");

        configuration.addAllowedHeader("*");

        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.DELETE);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**",
                "/js/**",
                "/swagger-ui"
        );
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.httpBasic()
                .disable()  // Default Login Form Disable
                .csrf().disable()   // CSRF Disabled. (TO-DO)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Not used session
                .and()
                .cors().configurationSource(corsConfigurationSource())  // CORS
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/user/add").permitAll()
                .anyRequest().authenticated();  // 그 외 요청은 모두 인증된 사용자만 가능
    }
}
