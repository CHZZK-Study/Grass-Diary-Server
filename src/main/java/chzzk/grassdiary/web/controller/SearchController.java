package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/search")
public class SearchController {
    TagService tagService;

    /**
     * 유저의 해시태그 리스트 반환
     * @param memberId
     * @return List<TagDTO>
     */
    @GetMapping("hashTag/{memberId}")
    public ResponseEntity<?> searchHashTag(@PathVariable Long memberId) {
        return ResponseEntity.ok(tagService.getMemberTags(memberId));
    }

    /**
     * 유저가 해시태그를 선택하면 그에 대한 다이어리 반환
     */
}
