package idv.kuma.ithelp2024.observer.jackpot;

public interface JackpotPoolRepository {
    void save(JackpotPool jackpotPool);

    JackpotPool findById(long id);
}
