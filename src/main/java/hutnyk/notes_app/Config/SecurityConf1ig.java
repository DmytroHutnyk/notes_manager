//package hutnyk.notes_app.Config;
//
//import lombok.SneakyThrows;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//
//    @Bean
//    @SneakyThrows
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
//        http.csrf(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/db").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//
//
//    @Bean
//    @SneakyThrows
//    public AuthenticationManager authenticationManager(HttpSecurity http, DataSource dataSource) {
//        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT username, password, is_enable FROM users WHERE username = ?")
//                .authoritiesByUsernameQuery(
//                        "SELECT u.username, r.name AS authority " +
//                                "FROM users u " +
//                                "JOIN user_role ur ON u.id = ur.user_id " +
//                                "JOIN role r ON ur.role_id = r.id " +
//                                "WHERE u.username = ?"
//                );
//
//
//        return builder.build();
//    }
//
//
////    @Bean
////    public AuthenticationManager authenticationManager(){
////        return authentication -> {
////
////        }
////    }
//}
//
////    @Bean
////    @SneakyThrows
////    public SecurityFilterChain securityFilterChain(HttpSecurity http, DataSource dataSource) {
////            http.csrf(Customizer.withDefaults())
////                    .httpBasic(Customizer.withDefaults())
////                    .formLogin(form -> form
////                            .loginPage("/login")
////                            .permitAll())
//////                    .logout(logout -> logout
//////                            .logoutUrl("/logout")                // Specify the logout URL
//////                            .logoutSuccessUrl("/login?logout")   // Redirect after successful logout
//////                            .permitAll()                         // Allow access to /logout without authentication
//////                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // Allow GET requests
//////                    )
////                    .authorizeHttpRequests(authorize -> authorize
////                            .anyRequest().authenticated()
////                    );
////
////        return http.build();
////    }