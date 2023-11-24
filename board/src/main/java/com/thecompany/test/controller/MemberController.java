package com.thecompany.test.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thecompany.test.dto.JwtResponse;
import com.thecompany.test.dto.MemberSignInResquestDto;
import com.thecompany.test.dto.MemberSignUpRequestDto;
import com.thecompany.test.dto.RefreshTokenRequest;
import com.thecompany.test.entity.RefreshToken;
import com.thecompany.test.service.MemberService;
import com.thecompany.test.service.RefreshTokenService;
// import com.thecompany.test.dto.MemberDTO;
import com.thecompany.test.service.Impl.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    
//    회원 가입 폼
    @PostMapping("/joinForm")
    public String joinForm() {
    	return "join";
    }
    
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public String join(@Valid @RequestBody MemberSignUpRequestDto request) throws Exception {
    	memberService.signUp(request);
    	return "redirect:/";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody MemberSignInResquestDto request) throws Exception {

        String accessToken = memberService.signIn(request);
        String refreshToken = refreshTokenService.createRefreshToken(request.getEmail()).getToken();
        JwtResponse jwtResponse = JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

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
