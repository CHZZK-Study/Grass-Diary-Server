package chzzk.grassdiary.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m.rewardPoint FROM Member m WHERE m.id = :memberId")
    Integer findRewardPointById(Long memberId);
}
