package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.diary.DiaryService;
import chzzk.grassdiary.web.dto.diary.DiaryDTO;
import chzzk.grassdiary.web.dto.diary.DiarySaveDTO;
import chzzk.grassdiary.web.dto.diary.DiaryUpdateDTO;
import chzzk.grassdiary.web.dto.diary.PopularDiaryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/diary")
@Tag(name = "일기 컨트롤러")
public class DiaryController {
    private final DiaryService diaryService;

    /**
     * CRUD
     */
    @PostMapping
    public Long save(@RequestBody DiarySaveDTO.Request requestDto) {
        return diaryService.save(requestDto);
    }

    @PatchMapping("/{id}")
    public Long update(@PathVariable(name = "id") Long id, @RequestBody DiaryUpdateDTO.Request requestDto) {
        return diaryService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable(name = "id") Long id) {
        diaryService.delete(id);
        return id;
    }

    @GetMapping("/{id}")
    public DiarySaveDTO.Response findById(@PathVariable(name = "id") Long id) {
        return diaryService.findById(id);
    }

    /**
     * 유저별 일기장 메인 페이지 1) 디폴트 API(최신순 5개씩) : api/diary/main/1?page=0 2) 오래된 순 5개씩 :
     * api/diary/main/1?page=0&sort=createdAt,ASC
     */
    @GetMapping("/main/{memberId}")
    @Operation(
            summary = "유저별 일기장 메인 페이지",
            description =
                    "최신순 5개씩(`api/diary/main/{memberId}?page=0`), 오래된 순 5개씩(`api/diary/main/1?page=0&sort=createdAt,ASC`) "
                            + "\n페이지를 나타내는 변수가 있는데, 해당 표에는 바로 나타나지 않으니 실행 시켜보시는 것을 추천드립니다.")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = DiaryDTO.class)))
    public ResponseEntity<?> findAll(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(diaryService.findAll(pageable, memberId));
    }

    @GetMapping("/popularity/{memberId}")
    @Operation(
            summary = "대표 일기",
            description = "오늘의 좋아요 가장 많은 받은 일기 10개")
    @Parameter(name = "memberId", description = "멤버 아이디(해당 일기에 대한 작성자의 좋아요 유무를 판단하기 위해 필요)")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = PopularDiaryDTO.class)))
    public ResponseEntity<?> todayPopularDiary(@PathVariable Long memberId) {
        return ResponseEntity.ok(diaryService.popularDiary(memberId));
    }
}
