package com.example.guitar;


import com.example.guitar.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepo userRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepo UserRepo) {
        this.userDetailsService = userDetailsService;
        this.userRepo = UserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/test1").hasRole("USER")
//                .antMatchers("/test2").hasRole("ADMIN")
//                .antMatchers("/upload").hasRole("ADMIN")
//                .antMatchers("/gallery").hasRole("USER")
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .csrf().disable();
//    }
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/registry").permitAll()
            .and()
            .formLogin().permitAll()
            .and()
            .csrf().disable();
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void add() {
//       UserApp UserappUser= new UserApp("UserMichal", passwordEncoder().encode("UserMichal"), "ROLE_USER");
//       UserApp UserAppAdmin = new UserApp("AdminMichal", passwordEncoder().encode("AdminMichal"), "ROLE_ADMIN");
//       userRepo.save(UserappUser);
//       userRepo.save(UserAppAdmin);
//    }
}
