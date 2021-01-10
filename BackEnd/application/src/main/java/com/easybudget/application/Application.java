package com.easybudget.application;

import com.easybudget.user.auth.JwtTokenAuthorizationOncePerRequestFilter;
import com.easybudget.user.auth.JwtUnAuthorizedResponseAuthenticationEntryPoint;
import com.easybudget.user.auth.exception.AuthenticationException;
import com.easybudget.user.auth.service.AuthPersonServiceDetail;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication(scanBasePackages = {"com.easybudget"})
@EnableJpaRepositories(basePackages = "com.easybudget")
@EntityScan(basePackages = "com.easybudget")
@EnableTransactionManagement
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application extends WebSecurityConfigurerAdapter {

    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;
    private AuthPersonServiceDetail authPersonServiceDetail;
    private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;
    private PersonService personService;

    @Autowired
    public Application(JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint, AuthPersonServiceDetail authPersonServiceDetail, JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter, PersonService personService) {
        this.jwtUnAuthorizedResponseAuthenticationEntryPoint = jwtUnAuthorizedResponseAuthenticationEntryPoint;
        this.authPersonServiceDetail = authPersonServiceDetail;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.personService = personService;
    }

    @Value("${jwt.get.token.uri}")
    private String authenticationPath;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authPersonServiceDetail).passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().exceptionHandling()
                .authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().anyRequest()
                .authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers().frameOptions().sameOrigin() // H2 Console Needs this setting
                .cacheControl(); // disable caching
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(HttpMethod.POST, authenticationPath)
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .and().ignoring()
                .antMatchers(HttpMethod.GET, "/**" // Other Stuff You want to Ignore
                ).and().ignoring()
                .antMatchers("/h2-console/**/**");// Should not be done in Production!
    }

    @Bean
    public Function<UserDetails, Person> fetchUser() {
        return (principal -> {
            String username = principal.getUsername();
            Optional<Person> person = personService.findByUsername(username);
            return person.orElseThrow(() -> new AuthenticationException("No user found, please login.", new IllegalAccessError()));
        });
    }

}
