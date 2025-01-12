package hutnyk.notes_app.Security.Config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig  {
    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
//                .authorizeHttpRequests(authorizeRequest ->
//                        authorizeRequest
//                                .requestMatchers("/auth/**","/admin/**", "/error", "/db/**", "/", "/save-user/**").permitAll()
//                                .anyRequest().authenticated()
//                )
                .authorizeHttpRequests(authorizeRequest ->
                 authorizeRequest
                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/auth/**", "/error", "/db/**", "/", "/save-user/**").permitAll()//TODO save user???!!!
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/db/**")
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/process_login")
                                .defaultSuccessUrl("/menu", true)
                                .failureUrl("/auth/login?error")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/auth/logout")
                                .logoutSuccessUrl("/auth/login")
                        );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/db/**"));
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .requestMatchers("/db/**", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
//    }
}
//.authorizeHttpRequests(authorizeRequest ->
//        authorizeRequest
//        .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/auth/**", "/error", "/db/**", "/", "/save-user/**").permitAll()
//                                .anyRequest().authenticated()
//                )