package idv.kuma.ithelp2024.observer.jackpot;

import java.util.HashMap;
import java.util.Map;

public class InMemoryJackpotPoolRepository implements JackpotPoolRepository {

    Map<Long, JackpotPool> jackpotPools = new HashMap<>();

    @Override
    public void save(JackpotPool jackpotPool) {
        jackpotPools.put(jackpotPool.getId(), jackpotPool);
    }

    @Override
    public JackpotPool findById(long id) {
        return jackpotPools.get(id);
    }
}
