package chzzk.grassdiary.service;

import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.web.dto.share.AllLatestDiariesDto;
import chzzk.grassdiary.web.dto.share.Top10DiariesDto;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShareService {
    private static final Direction SORT_DIRECTION_DESC = Direction.DESC;
    private static final String CREATED_AT_PROPERTY = "createdAt";
    private static final int NUMBER_ZERO = 0;
    private static final int PAGE_SIZE_TEN = 10;
    private final DiaryRepository diaryRepository;

    public List<Top10DiariesDto> findTop10DiariesThisWeek() {
        LocalDateTime startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay();
        LocalDateTime endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX);

        PageRequest pageRequest = PageRequest.of(NUMBER_ZERO, PAGE_SIZE_TEN);
        Page<Diary> diariesPage = diaryRepository.findTop10DiariesThisWeek(startOfWeek, endOfWeek, pageRequest);

        return Top10DiariesDto.of(diariesPage);
    }

    public AllLatestDiariesDto findLatestDiariesAfterCursor(Long cursorId, int size) {
        PageRequest pageRequest = PageRequest.of(
                NUMBER_ZERO,
                size + 1,
                Sort.by(SORT_DIRECTION_DESC, CREATED_AT_PROPERTY));

        List<Diary> diaries = diaryRepository.findLatestDiaries(cursorId, pageRequest);

        boolean hasMore = diaries.size() == size + 1;
        int endIndex = Math.min(diaries.size(), size);
        List<Diary> originDiaries = new ArrayList<>(diaries.subList(0, endIndex));

        return AllLatestDiariesDto.of(originDiaries, hasMore);
    }
}