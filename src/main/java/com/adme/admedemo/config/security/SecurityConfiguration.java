package com.adme.admedemo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .disable() // rest api 에서는 csrf 공격으로부터 안전하고 매번 api 요청으로부터 csrf 토큰을 받지 않아도 되어 disable로 설정
                .sessionManagement() // Rest Api 기반 애플리케이션 동작 방식 설정
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 사용하지 않아 STATELESS 로 설정

        // 클릭재킹 공격으로부터 보안 설정
        httpSecurity.headers()
                .frameOptions()
                .disable();

        // 접근 설정
        httpSecurity.authorizeRequests() // 시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미
//                .antMatchers("/guest/**").permitAll()
//                .antMatchers("/manager/**").hasRole("MANAGER") // 특정 권한을 가지는 사용자만 접근 가능
//                .antMatchers("/admin/**").hasRole("ADMIN") // 특정 권한을 가지는 사용자만 접근 가능
//                .antMatchers("/anonymous*").anonymous() // 인증되지 않은 사용자도 접근 가능
//                .anyRequest().authenticated(); // 모든 리소스를 의미, 접근허용 리소스 및 인증후 특정 레벨의 권한을 가진 사용자만 접근가능한 리소스를 설정하고 그외 나머지 리소스들은 무조건 인증을 완료해야 접근이 가능
                .antMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll() // 권한 허용 URL 설정
                .antMatchers("/v2/api-docs", "/swagger-resources", "/swagger*/**", "/swagger-ui.html", "/webjars/**",
                        "/swagger/**", "/sign-api/exception").permitAll() // 권한 허용 URL 설정
                .antMatchers(HttpMethod.GET, "/product/**").permitAll() // product 로 시작하는 GET 요청 허용
                .antMatchers("**exception**" ).permitAll() // 'exception' 단어가 들어간 경로는 모두 허용
                .anyRequest().hasRole("ADMIN"); // 기타 요청은 인증 권한을 가진 사용자에게 허용

        // 로그인 처리
        httpSecurity.formLogin()
//                .loginPage("/login") // 따로 설정한 로그인 페이지 사용시
//                .loginProcessingUrl("/perform_login") // 사용자 이름과 암호를 제출할 URL = 인증 처리를 하는 URL을 설정
                .defaultSuccessUrl("/tenseconds") // 정상적으로 인증 성공 했을 경우 이동하는 페이지
//                .successHandler(new CustomAuthenticationSuccessHandler("/main")) // 정상적인증 성공 후 별도의 처리가 필요한경우
//                .failureUrl("/login") // 로그인 실패 후 방문 페이지
//                .failureHandler(authenticationFailureHandler("/login_fail")) // 실패 후 별도의 처리가 필요한경우
                .permitAll(); // 인증 절차 없이 허용
//
        // 로그아웃 처리
        httpSecurity.logout()
////                .logoutUrl("/logout") // 로그아웃 처리 URL
////                .logoutSuccessUrl("/login") // 로그아웃 처리 후 이동할 URL
////                .deleteCookies("JSESSIONID","remember-me") // 쿠키 삭제
////                .logoutSuccessHandler(new LogoutSuccessHandler()) // 로그아웃 성공 후 핸들러
////                .deleteCookies("remember-me") // 쿠키삭제
                .invalidateHttpSession(true) // 브라우저가 완전히 종료되면 로그인한 정보를 지운다.
                .permitAll();

        // 로그인 정보를 기억하는 설정 (권장하지 않는다)
//        http.rememberMe()
//                .rememberMeParameter("remember-me") // 기본 파라미터명은 remember-me
//                .tokenValiditySeconds(3600) // default는 14일
//                .alwaysRemember(true) // remember me 기능이 활성화되지 않아도 항상 실행
//                .userDetailsService(userDetailService); // Remember me에서 시스템에 있는 사용자 계정을 조회할때 사용할 클래스

        // exception Custom 설정
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        // JWT Token 필터를 id/password 인증 필터 이전에 추가
        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
