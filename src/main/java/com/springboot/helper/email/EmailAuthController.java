package com.springboot.helper.email;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.EmailVerificationException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.repository.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Tag(name = "이메일 인증 컨트롤러", description = "이메일 인증 관련 컨트롤러")
@RestController
@RequestMapping("/auth/email")
public class EmailAuthController {
    private final RedisTemplate<String, String> redisTemplate;
    private final EmailSender emailSender;
    private final MemberRepository memberRepository;

    public EmailAuthController(RedisTemplate<String, String> redisTemplate, EmailSender emailSender, MemberRepository memberRepository) {
        this.redisTemplate = redisTemplate;
        this.emailSender = emailSender;
        this.memberRepository = memberRepository;
    }

    @Operation(summary = "이메일 인증 코드 발송", description = "입력한 이메일로 인증 코드를 전송합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증번호 전송 성공"),
            @ApiResponse(responseCode = "400", description = "이메일 형식 오류 또는 요청 값 유효성 실패"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 (이메일 발송 실패 등)")
    })
    @PostMapping("/verify/register")
    public ResponseEntity sendVerificationCodeRegister(@RequestBody EmailDto.Request dto) throws InterruptedException {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            // 이메일 이미 존재 → 409 Conflict 반환
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }

        String code = generateCode(); // ex: 6자리
        redisTemplate.opsForValue().set(dto.getEmail(), code, 3, TimeUnit.MINUTES);
        emailSender.sendEmail(
                new String[]{dto.getEmail()},
                "쿡킹 🍳 회원가입을 위한 인증번호 안내드립니다",
                "인증번호는 " + code + " 입니다.",
                "email-verification"
        );
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 인증 코드 발송(회원 정보 수정, 회원 탈퇴)", description = "입력한 이메일로 인증 코드를 전송합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증번호 전송 성공"),
            @ApiResponse(responseCode = "400", description = "이메일 형식 오류 또는 요청 값 유효성 실패"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 (이메일 발송 실패 등)")
    })
    @PostMapping("/verify")
    public ResponseEntity sendVerificationCode(@RequestBody EmailDto.Request dto) throws InterruptedException {
        String code = generateCode(); // ex: 6자리
        redisTemplate.opsForValue().set(dto.getEmail(), code, 3, TimeUnit.MINUTES);
        emailSender.sendEmail(
                new String[]{dto.getEmail()},
                "쿡킹 🍳 회원가입을 위한 인증번호 안내드립니다",
                "인증번호는 " + code + " 입니다.",
                "email-verification"
        );
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이메일 인증 코드 검증", description = "입력한 인증 코드가 일치하는지 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증번호 확인 성공"),
            @ApiResponse(responseCode = "401", description = "인증번호가 틀리거나 만료됨"),
            @ApiResponse(responseCode = "400", description = "요청 데이터 유효성 검사 실패"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    })
    @PostMapping("/confirm")
    public ResponseEntity checkVerificationCode(@RequestBody EmailDto.Confirm dto) {
        String savedCode = redisTemplate.opsForValue().get(dto.getEmail());
        if (savedCode == null || !savedCode.equals(dto.getCode())) {
            throw new EmailVerificationException();
        }
        redisTemplate.delete(dto.getEmail()); // 인증 완료되면 제거
        redisTemplate.opsForValue().set(dto.getEmail() + ":verified", "true", 10, TimeUnit.MINUTES);
        return ResponseEntity.ok().build();
    }

    // 인증번호 6자리 숫자 생성
    private String generateCode() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000); // 100000 ~ 999999
        return String.valueOf(number);
    }
}
