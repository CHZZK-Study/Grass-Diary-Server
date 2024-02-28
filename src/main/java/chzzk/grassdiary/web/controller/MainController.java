package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.MainService;
import chzzk.grassdiary.service.diary.DiaryService;
import chzzk.grassdiary.web.dto.diary.CountAndMonthGrassDTO;
import chzzk.grassdiary.web.dto.main.TodayInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/main")
@Tag(name = "메인 페이지에서 사용하는 컨트롤러")
public class MainController {
    private final MainService mainService;
    private final DiaryService diaryService;

    @GetMapping("/todayInfo")
    @Operation(summary = "오늘 정보", description = "date(String:`오늘은 MM월 dd일입니다.`), todayQuestion(String):`오늘의 질문`")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = TodayInfoDTO.class)))
    public ResponseEntity<?> todayInfo() {
        return ResponseEntity.ok(mainService.getTodayInfo());
    }

    @GetMapping("/grass/{memberId}")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = CountAndMonthGrassDTO.class)))
    @Operation(
            summary = "사용자의 현재 총 일기 개수, 이번 달 잔디 기록",
            description = "사용자의 현재까지의 총 잔디(일기) 개수, 이번달 잔디 기록")
    @Parameter(name = "memberId", description = "멤버 아이디")
    public ResponseEntity<?> getGrassCountAndMonthGrass(@PathVariable Long memberId) {
        return ResponseEntity.ok(diaryService.countAllAndMonthGrass(memberId));
    }
}
