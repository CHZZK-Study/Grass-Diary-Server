package chzzk.grassdiary.domain.reward;

import lombok.Getter;

@Getter
public enum RewardType {
    PLUS_DIARY_WRITE("일기 작성"),
    MINUS_SHOP_PURCHASE("상점 구매");
    private final String name;

    RewardType(String name) {
        this.name = name;
    }
}
