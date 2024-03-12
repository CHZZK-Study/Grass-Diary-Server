package chzzk.grassdiary.auth.controller;

import chzzk.grassdiary.auth.common.AuthenticatedMember;
import chzzk.grassdiary.auth.service.dto.AuthMemberPayload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthTestController {

    @GetMapping("/api/test")
    public String test(@AuthenticatedMember AuthMemberPayload authMemberPayload) {
        System.out.println("member = " + authMemberPayload);
        return "hello!";
    }
}
