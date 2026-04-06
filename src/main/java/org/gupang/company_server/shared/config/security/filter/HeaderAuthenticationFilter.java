package org.gupang.company_server.shared.config.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class HeaderAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. 헤더에서 유저 정보 추출 (GateWay 등에서 설정해준 이름과 맞춰야 함)
        String userId = request.getHeader("X-User-KeycloakId");
        String userRole = request.getHeader("X-User-Role");

        // 2. 유저 정보가 있다면 인증 객체 생성
        if (userId != null && userRole != null) {
            // 권한 설정 (Spring Security는 기본적으로 "ROLE_" 접두사를 사용함)
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userRole));

            // UserDetails 객체 생성 (비밀번호는 인증이 완료된 상태이므로 빈 값을 넣음)
            UserDetails userDetails = new User(userId, "", authorities);

            // 인증 토큰 생성
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 3. SecurityContext에 등록 (이후 SecurityUtil에서 꺼낼 수 있게 됨)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 4. 다음 필터로 진행
        filterChain.doFilter(request, response);
    }
}
