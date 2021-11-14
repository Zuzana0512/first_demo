package nl.novi.webshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/customer/id/orders").hasAnyRole("ADMIN", "FINADMIN", "PRODUCTMANAGER", "USER")
                .antMatchers("/customers/**").hasAnyRole("ADMIN", "FINADMIN")
                .antMatchers("/stocks/**").hasAnyRole("ADMIN", "PRDDUCTMANAGER")
                .antMatchers(GET,"/products/**").hasAnyRole("USER", "ADMIN", "PRODUCTMANAGER")
                .antMatchers("/products/**").hasAnyRole("ADMIN", "PRODUCTMANAGER")
                .antMatchers("/payments/**").hasAnyRole("ADMIN", "FINADMIN")
                .antMatchers(GET,"/orders/**").hasAnyRole("ADMIN", "PRODUCTMANAGER")
                .antMatchers(POST,"/orders/**").hasAnyRole("ADMIN", "PRODUCTMANAGER")
                .antMatchers("/orders/**").hasAnyRole("ADMIN", "FINADMIN")
                .antMatchers(GET, "/orders/id").hasAnyRole("ADMIN", "FINADMIN", "PRODUCTMANAGER")
                .antMatchers("/suppliers/**").hasAnyRole("ADMIN", "PRODUCTMANAGER")
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/home").permitAll()
                .antMatchers(GET, "/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable();

    }

}
