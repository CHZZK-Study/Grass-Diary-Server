package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    MyPageService myPageService;

    @GetMapping("profile/{memberId}")
    public ResponseEntity<?> getProfileInfo(@PathVariable Long memberId) {
        return ResponseEntity.ok(myPageService.findProfileById(memberId));
    }
}
