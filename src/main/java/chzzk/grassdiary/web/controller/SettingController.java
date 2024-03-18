package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.auth.common.AuthenticatedMember;
import chzzk.grassdiary.auth.service.dto.AuthMemberPayload;
import chzzk.grassdiary.service.SettingService;
import chzzk.grassdiary.web.dto.member.MemberIdDto;
import chzzk.grassdiary.web.dto.member.MemberUpdateRequest;
import chzzk.grassdiary.web.dto.member.MemberUpdatedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 정보 컨트롤러")
@RestController
@RequiredArgsConstructor
public class SettingController {
    private final SettingService settingService;

    @PutMapping("/api/members/me")
    @Operation(summary = "사용자 정보 수정", description = "닉네임, 소개글 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberUpdatedResponse.class))})
    })
    public ResponseEntity<MemberUpdatedResponse> updateMemberInfo(
            @Parameter(hidden = true) @AuthenticatedMember AuthMemberPayload payload,
            @RequestBody MemberUpdateRequest request) {
        MemberUpdatedResponse memberInfo = settingService.updateMemberInfo(payload.id(), request);
        return ResponseEntity.ok(memberInfo);
    }

    @Operation(summary = "사용자 정보 조회", description = "member의 id 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberIdDto.class))})
    })
    @GetMapping("/api/me")
    public ResponseEntity<MemberIdDto> findMemberInfo(
            @Parameter(hidden = true) @AuthenticatedMember AuthMemberPayload payload) {
        MemberIdDto memberInfo = settingService.findMemberInfo(payload.id());
        return ResponseEntity.ok(memberInfo);
    }
}
