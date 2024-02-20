package chzzk.grassdiary.service.diary;

import chzzk.grassdiary.domain.diary.Diary;
import chzzk.grassdiary.domain.diary.DiaryImageRepository;
import chzzk.grassdiary.domain.diary.DiaryLikeRepository;
import chzzk.grassdiary.domain.diary.DiaryRepository;
import chzzk.grassdiary.domain.member.MemberRepository;
import chzzk.grassdiary.web.dto.diary.DiaryDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional  // @Transactional을 클래스 단위로 하는 것과 메서드 단위로 하는 것 공부
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryLikeRepository diaryLikeRepository;
    private final DiaryImageRepository diaryImageRepository;
    private final MemberRepository memberRepository;

    // CREATE 일기 저장 후 id 반환(Redirect를 위해?)
    @Transactional
    public Long save(DiaryDto.Request requestDto) {
        return diaryRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, DiaryDto.Request requestDto) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 존재하지 않습니다. id = " + id));

        diary.update(requestDto.getContent(), requestDto.getIsPrivate(), requestDto.getHasImage(),
                requestDto.getHasTag(), requestDto.getConditionLevel());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 존재하지 않습니다. id = " + id));

        diaryRepository.delete(diary);
    }

    @Transactional()
    public DiaryDto.Response findById(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일기가 존재하지 않습니다. id = " + id));
        //조회한 결과를 담은 DTO 객체를 생성해서 반환
        return new DiaryDto.Response(diary);
    }


}
