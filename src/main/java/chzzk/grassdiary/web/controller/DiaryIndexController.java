package chzzk.grassdiary.web.controller;

import chzzk.grassdiary.service.diary.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DiaryIndexController {

    private final DiaryService diaryService;

//    @GetMapping("/")
//    public String index()
}

