package idv.kuma.ithelp2024.responsibility.chain.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeBonusRepository implements BonusRepository {
    private Map<String, Bonus> bonuses = new HashMap<>();

    @Override
    public void save(Bonus bonus) {

        this.bonuses.put(bonus.bonusCode(), bonus);

    }

    @Override
    public Optional<Bonus> findByCode(String bonusCode) {
        return Optional.ofNullable(this.bonuses.get(bonusCode));
    }
}
