package idv.kuma.ithelp2024.responsibility.chain.reference;

import java.util.Optional;

public interface BonusRepository {
    void save(Bonus bonus);

    Optional<Bonus> findByCode(String bonusCode);
}
