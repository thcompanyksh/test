package com.example.jwtv1.controller;

import com.example.jwtv1.dto.JwtResponse;
import com.example.jwtv1.dto.MemberSignInResquestDto;
import com.example.jwtv1.dto.MemberSignUpRequestDto;
import com.example.jwtv1.dto.RefreshTokenRequest;
import com.example.jwtv1.entity.RefreshToken;
import com.example.jwtv1.jwt.JwtUtil;
import com.example.jwtv1.service.MemberService;
import com.example.jwtv1.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@ModelAttribute MemberSignInResquestDto request) throws Exception {

        String accessToken = memberService.signIn(request);
        String refreshToken = refreshTokenService.createRefreshToken(request.getEmail()).getToken();
        JwtResponse jwtResponse = JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.ok().body(jwtResponse);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long join(@Valid @ModelAttribute MemberSignUpRequestDto request) throws Exception {
        return memberService.signUp(request);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@ModelAttribute RefreshTokenRequest refreshTokenRequest) {
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