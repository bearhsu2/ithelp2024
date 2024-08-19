package idv.kuma.ithelp2024.responsibility.chain.reference;

import java.util.Optional;

public interface DrawBonusRecordRepository {
    Optional<DrawBonusRecord> find(long userId, String bonusCode);

    void save(DrawBonusRecord drawBonusRecord);
}
