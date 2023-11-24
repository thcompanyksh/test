package com.example.jwtv2.jwt;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwtv2.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
	
	// private final MemberService memberService;
	
	@Value("${jwt.secret}")
	private final String secretKey;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		logger.info("authorization : " + authorization);
		
		if(authorization == null || !authorization.startsWith("Bearer ")){
            // logger.error("authorization을 잘못 보냈습니다.");
            filterChain.doFilter(request, response);
            return;
        }
		
		String token = authorization.split(" ")[1];
		logger.info("token : " + token);
		
        //token Expired되었는지
        if(JwtUtil.isExpired(token, secretKey)){
            logger.error("Token이 만료 되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        //username token에서 꺼내기
        String email = JwtUtil.getUserName(token, secretKey);
        logger.info("email: " + email);

        //인증된 사용자를 나타내는 토큰 객체를 생성하고, 권한 정보를 설정
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority("USER")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
		
	}

}
