package chzzk.grassdiary.service;

import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.web.dto.share.LatestDiariesDto;
import chzzk.grassdiary.web.dto.share.Top10DiariesDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShareService {
    private static final Direction SORT_DIRECTION_DESC = Direction.DESC;
    private static final String LIKE_PROPERTY = "diaryLikes";
    private static final String CREATED_AT_PROPERTY = "createdAt";
    private static final int PAGE_NUMBER_ZERO = 0;
    private static final int PAGE_SIZE = 10;
    private final DiaryRepository diaryRepository;

    public List<Top10DiariesDto> findTop10Diaries() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

        PageRequest pageRequest = PageRequest.of(PAGE_NUMBER_ZERO, PAGE_SIZE,
                Sort.by(SORT_DIRECTION_DESC, LIKE_PROPERTY, CREATED_AT_PROPERTY));
        Page<Diary> diariesPage = diaryRepository.findByIsPrivateFalseAndCreatedAtBetween(startOfDay, endOfDay,
                pageRequest);

        return diariesPage.stream()
                .map(d -> new Top10DiariesDto(
                        d.getId(),
                        d.getContent(),
                        d.getDiaryLikes().size(),
                        d.getMember().getNickname()))
                .toList();
    }

    public Page<LatestDiariesDto> findLatestDiaries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(SORT_DIRECTION_DESC, LIKE_PROPERTY, CREATED_AT_PROPERTY));
        Page<Diary> diariesPage = diaryRepository.findByIsPrivateFalse(pageable);

        return diariesPage.map(d -> new LatestDiariesDto(
                d.getId(),
                d.getContent(),
                d.getDiaryLikes().size(),
                d.getMember().getNickname())
        );
    }
}
