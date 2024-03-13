package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.ShareService;
import chzzk.grassdiary.web.dto.share.LatestDiariesDto;
import chzzk.grassdiary.web.dto.share.Top10DiariesDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/share")
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @GetMapping("/popularity")
    @Operation(summary = "Top10 API", description = "이번 주에 공개된 일기 중 좋아요 순, 작성 순으로 10개의 일기를 보여준다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Top10DiariesDto.class))})
    })
    public ResponseEntity<List<Top10DiariesDto>> showTop10ThisWeek() {
        List<Top10DiariesDto> top10Diaries = shareService.findTop10DiariesThisWeek();
        return ResponseEntity.ok(top10Diaries);
    }

    @GetMapping("/latest")
    @Operation(summary = "최신 순 일기 필드 API", description = "이번 주에 공개된 일기를 최신순으로 보여준다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LatestDiariesDto.class))})
    })
    public ResponseEntity<List<LatestDiariesDto>> showLatestDiariesAfterCursor(
            @RequestParam(value = "cursorId", required = false, defaultValue = Long.MAX_VALUE + "") Long cursorId,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {

        List<LatestDiariesDto> latestDiaries = shareService.findLatestDiariesAfterCursor(cursorId, size);
        return ResponseEntity.ok(latestDiaries);
    }
}
