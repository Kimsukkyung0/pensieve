package com.example.pensieve.common.security;

import com.example.pensieve.common.config.RedisService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redis;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, MvcRequestMatcher.Builder mvc) throws Exception{
        httpSecurity.authorizeHttpRequests(authz ->
                        authz.requestMatchers(
                                        mvc.pattern("/swagger.html"), mvc.pattern("/swagger-ui/**"), mvc.pattern("/v3/api-docs/**"),
                                        mvc.pattern("/index.html"), mvc.pattern("/"), mvc.pattern("/static/**"),
                                        mvc.pattern("**exception**"),
                                        mvc.pattern(HttpMethod.POST, "/api/refresh-token"),
                                        mvc.pattern(HttpMethod.POST, "/api/admin/refresh-token")
                                ).permitAll()
                                .anyRequest().permitAll()
                ) //사용 권한 체크
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 사용 X
                .httpBasic(http -> http.disable()) //UI 있는 시큐리티 설정을 비활성화
                .csrf(csrf -> csrf.disable()) //CSRF 보안이 필요 X, 쿠키와 세션을 이용해서 인증을 하고 있기 때문에 발생하는 일, https://kchanguk.tistory.com/197
                .exceptionHandling(except -> {
                    except.accessDeniedHandler(new CustomAccessDeniedHandler());
                    except.authenticationEntryPoint(new MyAuthenticationEntryPoint());
                }).addFilterBefore(new JwtAuthFilter(jwtTokenProvider, redis), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }
    @Scope("prototype")
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

//    @Bean
//    public HandlerMappingIntrospector mvcHandlerMappingIntrospector(){
//        return new HandlerMappingIntrospector();
//    }
    //스프링 시큐리티는 root 컨텍스트에서 초기화 되고 mvc는 자식 컨텍스트에서 초기화 되므로 스프링 시큐리티가 자식놈인 mvc를 알 수 있는 방법이 없다.
    // 그래서 중간다리 역할을 해주는 HandlerMappingIntrospector 라는 역할이 필요하다.
}
