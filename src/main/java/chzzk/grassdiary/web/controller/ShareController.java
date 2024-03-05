package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.ShareService;
import chzzk.grassdiary.web.dto.share.LatestDiariesDto;
import chzzk.grassdiary.web.dto.share.Top10DiariesDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @Operation(summary = "Top10 API", description = "하루 기준 공개로 작성된 일기 중, 가장 많은 좋아요 수 순으로 10개의 일기 피드를 보여준다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Top10DiariesDto.class))})
    })
    public ResponseEntity<List<Top10DiariesDto>> showTop10() {
        List<Top10DiariesDto> top10Diaries = shareService.findTop10Diaries();
        return ResponseEntity.ok(top10Diaries);
    }

    @GetMapping("/latest")
    @Operation(summary = "최신 순 일기 필드 API", description = "공개로 작성된 일기를 최신순으로 보여준다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LatestDiariesDto.class))})
    })
    public ResponseEntity<Page<LatestDiariesDto>> showLatestDiaries(
            @RequestParam @Parameter(name = "page", description = "페이지 번호") int page,
            @RequestParam @Parameter(name = "size", description = "페이지 크기") int size) {

        Page<LatestDiariesDto> latestDiaries = shareService.findLatestDiaries(page, size);
        
        return ResponseEntity.ok(latestDiaries);
    }
}
