package idv.kuma.ithelp2024.responsibility.chain.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeDrawBonusRecordRepository implements DrawBonusRecordRepository {
    private List<DrawBonusRecord> records = new ArrayList<>();

    @Override
    public Optional<DrawBonusRecord> find(long userId, String bonusCode) {

        for (DrawBonusRecord record : records) {
            if (record.getUserId() == userId && record.getBonusCode().equals(bonusCode)) {
                return Optional.of(record);
            }
        }

        return Optional.empty();
    }

    @Override
    public void save(DrawBonusRecord drawBonusRecord) {

        this.records.add(drawBonusRecord);

    }
}
