package chzzk.grassdiary.domain.auth.controller;

import chzzk.grassdiary.domain.auth.common.AuthenticatedMember;
import chzzk.grassdiary.domain.auth.service.dto.AuthMemberPayload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthTestController {

    @GetMapping("/test")
    public String test(@AuthenticatedMember AuthMemberPayload authMemberPayload) {
        System.out.println("member = " + authMemberPayload);
        return "hello!";
    }
}
