package idv.kuma.ithelp2024.responsibility.chain.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeDrawBonusRecordRepository implements DrawBonusRecordRepository {
    private final List<DrawBonusRecord> records = new ArrayList<>();

    @Override
    public Optional<DrawBonusRecord> find(long userId, String bonusCode) {

        return records.stream()
                .filter(oneRecord -> oneRecord.matches(userId, bonusCode))
                .findFirst();

    }

    @Override
    public void save(DrawBonusRecord drawBonusRecord) {

        this.records.add(drawBonusRecord);

    }
}
