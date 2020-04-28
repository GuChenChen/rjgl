package com.fykj.scaffold.security.oauth2.config;

import com.fykj.scaffold.security.oauth2.access.MyUserDetailsServiceImpl;
import com.fykj.scaffold.security.oauth2.sms.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wangf
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;
    /**
     * 需要配置这个支持password模式
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * password 方案三：支持多种编码，通过密码的前缀区分编码方式,推荐
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(smsCodeAuthenticationProvider);
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf 伪造跨域
        http.csrf().disable();

        /**
         *  因为ResourceServerConfigurerAdapter的优先级高于WebSecurityConfigurerAdapter
         * 1.当ResourceServerConfiguration处理完请求，剩下的url在WebSecurityConfigurerAdapter中处理
         * 2.此处拦截“/oauth/authorize”处理
         * http.authorizeRequests().antMatchers("/oauth/authorize").authenticated().and().httpBasic();此句是处理ResourceServerConfigurerAdapter剩下的所有请求
         */
        http.antMatcher("/oauth/authorize").authorizeRequests().anyRequest().authenticated().and().httpBasic();


    }
}
