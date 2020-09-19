package pl.ubezpieczenia.strona.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    //DO TESTÓW
    private final String [] permissions = new String[]{
            "/resources/**",
            "/",
            "/css/**",
            "/js/**",
            "/showArticleDetails/**",
            "/kontakt",
            "/nowosci",
            "/o-mnie",
            "/oferta",
            "/register",
            "/error",
            "/error.html"

    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/dodaj_wpis").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/error")
                .permitAll()
                .loginProcessingUrl("/processlogin")
                .permitAll()
                .usernameParameter("user")
                .passwordParameter("pass")
                .and()
                .logout()
                .logoutUrl("/logmeout")
                .logoutSuccessUrl("/")
                .permitAll();
    }
}
