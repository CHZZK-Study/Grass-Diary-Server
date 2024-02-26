package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.MyPageService;
import chzzk.grassdiary.web.dto.member.GrassInfoDTO;
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
@RequestMapping("/api/grass")
@Tag(name = "잔디 컨트롤러")
public class GrassController {
    private final MyPageService myPageService;

    /**
     * 사용자의 전체 잔디
     */
    @GetMapping("{memberId}")
    @Operation(
            summary = "사용자 전체 잔디 불러오기",
            description = "")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = GrassInfoDTO.class)))
    @Parameter(name = "memberId", description = "멤버 아이디")
    public ResponseEntity<?> getGrassHistory(@PathVariable Long memberId) {
        return ResponseEntity.ok(myPageService.findGrassHistoryById(memberId));
    }
}
