package com.in28minutes.rest.webservices.restfulwebservices;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // security for web application
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Value("${openshift.user}")
//    private String user;
//
//    @Value("${openshift.password}")
//    private String pass;

   /* @Autowired
    UserDetailsService userDetailsService;*/



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set my configuration on the auth object

        /*auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());*/


        auth.inMemoryAuthentication()
              .withUser("user").password("user").roles("USER")
              .and()
              .withUser("admin").password("admin").roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/users/").hasRole("ADMIN")
                .antMatchers("/users/**").hasRole("USER")
                .and().formLogin();
    }

    /*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };*/

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}

