package s24.backend;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import s24.backend.web.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true) // Enables method-level security annotations like @Secured
public class WebSecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailsService; // Injects the custom UserDetailsService implementation

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Define which requests are permitted without authentication
                .requestMatchers("/login", "/main").permitAll() // Allow access to login and main pages
                .requestMatchers(antMatcher("/api/**")).permitAll() // Allow all API requests
                .requestMatchers(antMatcher("/css/**")).permitAll() // Allow access to CSS resources
                .requestMatchers("/admin/**").hasRole("ADMIN") // Restrict access to admin routes to users with ADMIN role
                .anyRequest().authenticated() // Require authentication for any other request
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/main", true) // Redirect to main page after successful login
                .failureUrl("/login?error=true") // Redirect to login page with error flag on failure
                .permitAll() // Allow access to the login page for everyone
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // Define the logout endpoint
                .logoutSuccessUrl("/login?logout") // Redirect to login page after successful logout
                .permitAll() // Allow everyone to access the logout endpoint
            )
            .httpBasic(httpBasic -> {}) // Enable basic HTTP authentication
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.disable()) // Disable frame options to allow framing (e.g., for H2 console)
            );

        return http.build(); // Build and return the SecurityFilterChain
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Use the custom UserDetailsService and BCrypt for password encoding
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
