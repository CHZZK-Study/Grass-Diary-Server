package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.diary.DiaryService;
import chzzk.grassdiary.web.dto.diary.DiarySaveDTO;
import chzzk.grassdiary.web.dto.diary.DiaryUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/diary")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

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
}
