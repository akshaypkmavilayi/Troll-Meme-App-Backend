package com.memeapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Contains user details - implementation class inside UserServiceImpl.java only contains one method loadUserByUsername(String username) */
    @Autowired
    private UserDetailsService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                /** facing an issue with CORS policy in frontend, added cors(Customizer.withDefaults()) to resolve this issue */
                .cors(Customizer.withDefaults())
                .authorizeRequests()
                .antMatchers("**/user/register", "/login", "/meme").permitAll() // Allow access to public endpoints
                .antMatchers("/user/view-all-user").hasAnyRole("ADMIN") // Require authentication for protected endpoint
                .anyRequest().permitAll() // Allow access to all other endpoints
                .and()
                .formLogin().loginPage("/login").permitAll();
    }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/user/register").permitAll()
//                .antMatchers("/user/view-all-user").authenticated()
//                .anyRequest().hasAnyRole("USER")
//                .and()
//                .formLogin();
//
//
//
//        return http.build();
//    }

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("root")
//                .password("root")
//                .roles("USER")
//                .disabled(false)
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(userDetails);
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

}