package chzzk.grassdiary.domain.color;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorCodeRepository extends JpaRepository<ColorCode, Long> {
    Optional<ColorCode> findByColorName(String colorName);
}
