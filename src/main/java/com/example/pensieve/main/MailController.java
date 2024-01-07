package com.example.pensieve.main;


import com.example.pensieve.sign.SignService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping("/emails/verification-requests")
    public ResponseEntity sendMessage(@RequestParam String email) throws Exception {
        mailService.sendCodeToEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/emails/verifications")
//    public ResponseEntity verificationEmail(@RequestParam String email,
    public boolean verificationEmail(@RequestParam String email,
                                            @RequestParam("code") String authCode) throws Exception{
        boolean response = mailService.verifiedCode(email, authCode);
//        EmailVerificationResult response = mailService.verifiedCode(email, authCode);

//        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
        return response;
    }
}
