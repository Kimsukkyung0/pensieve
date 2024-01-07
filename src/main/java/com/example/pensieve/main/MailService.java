package com.example.pensieve.main;

import com.example.pensieve.common.config.RedisService;
import com.example.pensieve.common.entity.UserEntity;
import com.example.pensieve.common.repository.UserRepository;
import com.example.pensieve.main.model.EmailVerificationResult;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class MailService {
    private final JavaMailSender emailSender;
    private final RedisService redisService;
    private final UserRepository userRep;
    private final String AUTH_CODE_PREFIX = "PenSieveAutHCd4MailVerificatiOn";
    private final long AUTH_CODE_EXPIRATION_MILLIES = 300000;
//    인증코드 유효시간 : 5분

    public void sendEmail(String toEmail,
                          String title,
                          String text) throws Exception {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                    "title: {}, text: {}", toEmail, title, text);
            throw new Exception(e);
        }
    }

    // 발신할 이메일 데이터 세팅
    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }


    public void sendCodeToEmail(String toEmail) throws Exception {
        this.checkDuplicatedEmail(toEmail);
        String title = "Travel with me 이메일 인증 번호";
        String authCode = this.createCode();
        log.info("authCode : {}",authCode);
        log.info("toEmail (주소인듯) : {}",toEmail);
        this.sendEmail(toEmail, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisService.setMailVerificationData(AUTH_CODE_PREFIX + toEmail,
                authCode, Duration.ofMillis(this.AUTH_CODE_EXPIRATION_MILLIES));
    }

    private void checkDuplicatedEmail(String email) throws Exception{
        Optional<UserEntity> member = userRep.findByEmail(email);
        if (member.isPresent()) {
            log.debug("MemberServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
            throw new Exception();
        }
    }

    private String createCode() throws Exception {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("MemberService.createCode() exception occur");
            throw new Exception(e);
        }
    }

//    public EmailVerificationResult verifiedCode(String email, String authCode) throws Exception{
    public boolean verifiedCode(String email, String authCode) throws Exception{
        this.checkDuplicatedEmail(email);
        String redisAuthCode = redisService.getData(AUTH_CODE_PREFIX + email);
        boolean authResult = redisService.checkExistValue(redisAuthCode) && redisAuthCode.equals(authCode);
        //redis db에 key가 저장되어있고, key에 대한 value가 동일하다면 true
//        return EmailVerificationResult.of(authResult);
        return authResult;
    }
}

