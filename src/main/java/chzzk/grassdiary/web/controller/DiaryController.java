package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.service.diary.DiaryService;
import chzzk.grassdiary.web.dto.diary.DiaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/api/diary")
    public Long save(@RequestBody DiaryDto.Request requestDto) {
        return diaryService.save(requestDto);
    }

    @PatchMapping("/api/diary/{id}")
    public Long update(@PathVariable Long id, @RequestBody DiaryDto.Request requestDto) {
        return diaryService.update(id, requestDto);
    }

    @DeleteMapping("/api/diary/{id}")
    public Long delete(@PathVariable Long id) {
        diaryService.delete(id);
        return id;
    }

    @GetMapping("/api/diary/{id}")
    public DiaryDto.Response findById(@PathVariable Long id) {
        return diaryService.findById(id);
    }
}
