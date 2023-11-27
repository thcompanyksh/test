package com.thecompany.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thecompany.test.dto.MemberSignInResquestDto;
import com.thecompany.test.dto.MemberSignUpRequestDto;

public interface MemberService extends UserDetailsService {

    public String signIn(MemberSignInResquestDto requestDto) throws Exception;

    public Long signUp(MemberSignUpRequestDto requestDto) throws Exception;

    public String generateToken(String username);


}