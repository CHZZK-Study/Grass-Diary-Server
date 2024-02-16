package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {
    private final MainService mainService;

    @GetMapping("api/todayInfo")
    public ResponseEntity<?> todayInfo() {
        return ResponseEntity.ok(mainService.getTodayInfo());
    }
}
