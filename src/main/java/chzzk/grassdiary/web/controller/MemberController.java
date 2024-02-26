package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.MyPageService;
import chzzk.grassdiary.web.dto.member.MemberInfoDTO;
import chzzk.grassdiary.web.dto.member.TotalRewardDTO;
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
@RequestMapping("/api/member")
@Tag(name = "멤버 컨트롤러")
public class MemberController {
    private final MyPageService myPageService;

    @GetMapping("profile/{memberId}")
    @Operation(
            summary = "멤버의 프로필 기본 정보",
            description = "이미지 URL, 닉네임, 한줄 소개")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = MemberInfoDTO.class)))
    @Parameter(name = "memberId", description = "멤버 아이디")
    public ResponseEntity<?> getProfileInfo(@PathVariable Long memberId) {
        return ResponseEntity.ok(myPageService.findProfileById(memberId));
    }

    /**
     * 사용자의 리워드 포인트 반환
     */
    @GetMapping("totalReward/{memberId}")
    @Operation(
            summary = "멤버의 누적 리워드 정보",
            description = "")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = TotalRewardDTO.class)))
    @Parameter(name = "memberId", description = "멤버 아이디")
    public ResponseEntity<?> getTotalReward(@PathVariable Long memberId) {
        return ResponseEntity.ok(myPageService.findTotalRewardById(memberId));
    }
}
