package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.TagService;
import chzzk.grassdiary.web.dto.diary.DiaryDTO;
import chzzk.grassdiary.web.dto.diary.TagDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search")
@Tag(name = "검색 컨트롤러")
public class SearchController {
    private final TagService tagService;

    /**
     * 유저의 해시태그 리스트 반환
     */
    @GetMapping("hashTag/{memberId}")
    @Operation(
            summary = "유저가 사용한 해시태그 리스트",
            description = "")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = TagDTO.class)))
    @Parameter(name = "memberId", description = "멤버 아이디")
    public ResponseEntity<?> searchHashTag(@PathVariable Long memberId) {
        return ResponseEntity.ok(tagService.getMemberTags(memberId));
    }

    /**
     * 유저가 해시태그를 선택하면 그에 대한 다이어리 반환
     */
    @GetMapping("tagId/{memberId}")
    @Operation(
            summary = "해시태그에 대한 다이어리 검색",
            description = "유저가 해시태그를 선택하면 해당 해시태그를 사용한 다이어리 리스트를 반환")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = DiaryDTO.class)))
    @Parameters({
            @Parameter(name = "memberId", description = "멤버 아이디"),
            @Parameter(name = "tagId", description = "검색을 원하는 해시태그 아이디")
    })
    public ResponseEntity<?> findByHashTagId(@PathVariable Long memberId, @RequestParam Long tagId) {
        return ResponseEntity.ok(tagService.findByHashTagId(memberId, tagId));
    }
}
