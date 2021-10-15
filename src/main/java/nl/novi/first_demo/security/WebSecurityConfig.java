package nl.novi.first_demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.GET;

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

//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}p4ssw0rd").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}p4ssw0rd").roles("USER", "ADMIN")
//                .and()
//                .withUser("peter").password("{noop}p4ssw0rd").roles("CUSTOMER");

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/customers/**").hasAnyRole("ADMIN", "OWNER", "FINADMIN")
                .antMatchers("/stocks/**").hasAnyRole("ADMIN", "OWNER", "PRDDUCTBEHEER")
                .antMatchers(GET,"/products/**").hasRole("USER")
                .antMatchers("/products/**").hasAnyRole("ADMIN", "OWNER", "PRODUCTBEHEER")
                .antMatchers("/payment/**").hasAnyRole("ADMIN", "OWNER", "FINADMIN")
                .antMatchers("/orders/**").hasAnyRole("ADMIN", "OWNER", "FINADMIN", "PRODUCTBEHEER")
                .antMatchers("/suppliers/**").hasAnyRole("ADMIN", "OWNER", "FINADMIN", "PRODUCTBEHEER")
                .antMatchers("/users/**").hasAnyRole("ADMIN", "OWNER")
                .antMatchers("/").permitAll()
                .antMatchers(GET, "/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable();

    }

}
