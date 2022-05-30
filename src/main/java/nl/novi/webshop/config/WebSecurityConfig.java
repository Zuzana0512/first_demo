package nl.novi.webshop.config;

import nl.novi.webshop.filter.JwtRequestFilter;
import nl.novi.webshop.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/customer/id/orders").hasAnyRole("ADMIN", "FINADMIN", "PRODUCTMANAGER", "CUSTOMER")
                .antMatchers(HttpMethod.POST,"/customers/**").permitAll()
                .antMatchers("/customers/**").hasAnyRole("ADMIN", "FINADMIN")
                .antMatchers("/stocks/**").hasAnyRole("ADMIN", "PRDDUCTMANAGER")
                .antMatchers(HttpMethod.GET,"/products/**").permitAll()
                .antMatchers("/products/**").hasAnyRole("ADMIN", "PRODUCTMANAGER")
                .antMatchers("/payments/**").hasAnyRole("ADMIN", "FINADMIN")
                .antMatchers(HttpMethod.GET,"/orders/**").hasAnyRole("ADMIN", "PRODUCTMANAGER")
                .antMatchers(HttpMethod.POST,"/orders/**").hasAnyRole("ADMIN", "PRODUCTMANAGER")
                .antMatchers("/orders/**").hasAnyRole("ADMIN", "FINADMIN")
                .antMatchers(HttpMethod.GET, "/orders/id").hasAnyRole("ADMIN", "FINADMIN", "PRODUCTMANAGER")
                .antMatchers("/suppliers/**").hasAnyRole("ADMIN", "PRODUCTMANAGER")
                .antMatchers("/users/**").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers(HttpMethod.GET, "/**").authenticated()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().denyAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


    }

}
