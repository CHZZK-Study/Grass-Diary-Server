package chzzk.grassdiary.web.dto.share;

public record LatestMetaDto(
        int count,
        boolean hasMore
) {
    public static LatestMetaDto of(int count, boolean hasMore) {
        return new LatestMetaDto(
                count,
                hasMore
        );
    }
}
