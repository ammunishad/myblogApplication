package com.MyBlogApplication.Security;

import com.MyBlogApplication.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder getEncodedPassword(){
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    private CustomUserDetailsService userDetailService;
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                // This is known as Spring filter chain
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
                // if you want to make a bond that any a user or an Only an ADMIN AND USER can access the url than put
                //antMatchers(HttpMethod.GET,"/api/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(getEncodedPassword());
    }
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails user = User.builder().username("Nikhil").password(getEncodedPassword().encode("password")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(getEncodedPassword().encode("Admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }
}
