package hutnyk.notes_app.Config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
            http.csrf(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults())
                    .formLogin(Customizer.withDefaults())
                    .authorizeHttpRequests(authorize -> authorize
                            .anyRequest().authenticated()
                    );

        return http.build();
    }
}
