package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.DiaryService;
import chzzk.grassdiary.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/grass")
public class GrassController {
    private final MyPageService myPageService;

    /**
     * 사용자의 전체 잔디
     */
    @GetMapping("{memberId}")
    public ResponseEntity<?> getGrassHistory(@PathVariable Long memberId) {
        return ResponseEntity.ok(myPageService.findGrassHistoryById(memberId));
    }
}
