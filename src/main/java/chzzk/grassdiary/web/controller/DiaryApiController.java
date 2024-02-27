package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.diary.DiaryService;
import chzzk.grassdiary.web.dto.diary.DiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DiaryApiController {

    private final DiaryService diaryService;

    @PostMapping("/api/diary")
    public Long save(@RequestBody DiaryDto.Request requestDto) {
        return diaryService.save(requestDto);
    }

    @PatchMapping("/api/diary/{id}")
    public Long update(@PathVariable(name = "id") Long id, @RequestBody DiaryDto.Request requestDto) {
        return diaryService.update(id, requestDto);
    }

    @DeleteMapping("/api/diary/{id}")
    public Long delete(@PathVariable(name = "id") Long id) {
        diaryService.delete(id);
        return id;
    }

    @GetMapping("/api/diary/{id}")
    public DiaryDto.Response findById(@PathVariable(name = "id") Long id) {
        return diaryService.findById(id);
    }
}
