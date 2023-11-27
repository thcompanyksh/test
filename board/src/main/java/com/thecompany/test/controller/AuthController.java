package com.thecompany.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thecompany.test.dto.JwtResponse;
import com.thecompany.test.dto.MemberSignInResquestDto;
import com.thecompany.test.dto.RefreshTokenRequest;
import com.thecompany.test.entity.RefreshToken;
import com.thecompany.test.service.MemberService;
import com.thecompany.test.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	
	private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
	
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody MemberSignInResquestDto request) throws Exception {

        String accessToken = memberService.signIn(request);
        String refreshToken = refreshTokenService.createRefreshToken(request.getEmail()).getToken();
        JwtResponse jwtResponse = JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        System.out.println("request" + request);
        return ResponseEntity.ok().body(jwtResponse);
        
    }
    
    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getMember)
                .map(member -> {
                        String accessToken = memberService.generateToken(member.getEmail());
                        return ResponseEntity.ok().body(JwtResponse.builder()
                                .accessToken(accessToken)
                                .refreshToken(refreshTokenRequest.getToken())
                                .build());
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"
                ));
    }
}
