package com.amdtia.usermanagement.security;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(jdbcTemplate.getDataSource())
                .usersByUsernameQuery(
                        "select username,password, 'true' from user where username=?")
                .authoritiesByUsernameQuery(
                        "select username, title, 'true' from user u inner join groups_user gu on u.id = gu.users_id inner join groups g on gu.groups_id = g.id inner join groups_permissions gp on g.id = gp.group_id inner join permissions p on gp.permissions_id = p.id where username=?")
                .passwordEncoder(passwordEncoder());
    }


        @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/register"
                        ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/loginPage?logout")
                .permitAll();


        http.authorizeRequests().mvcMatchers("/*").authenticated().and().httpBasic();
    }

//    @Bean
//    @Override
//    public JdbcUserDetailsManager userDetailsService() {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
//        manager.setJdbcTemplate(jdbcTemplate);
//        return manager;
//    }

}