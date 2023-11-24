package com.example.jwtv1.service;

import com.example.jwtv1.dto.MemberSignInResquestDto;
import com.example.jwtv1.dto.MemberSignUpRequestDto;
import com.example.jwtv1.jwt.JwtUtil;
import com.example.jwtv1.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {


    public String signIn(MemberSignInResquestDto requestDto) throws Exception;


    public Long signUp(MemberSignUpRequestDto requestDto) throws Exception;

    public String generateToken(String username);


}