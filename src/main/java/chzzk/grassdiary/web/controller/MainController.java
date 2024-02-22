package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.DiaryService;
import chzzk.grassdiary.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/main")
public class MainController {
    private final MainService mainService;
    private final DiaryService diaryService;

    @GetMapping("/todayInfo")
    public ResponseEntity<?> todayInfo() {
        return ResponseEntity.ok(mainService.getTodayInfo());
    }

    /**
     * 사용자의 현재까지의 잔디(일기) 개수
     */
    @GetMapping("/grassCount/{memberId}")
    public ResponseEntity<?> getGrassCount(@PathVariable Long memberId) {
        return ResponseEntity.ok(diaryService.countAll(memberId));
    }
}
