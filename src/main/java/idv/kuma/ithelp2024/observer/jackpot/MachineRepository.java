package idv.kuma.ithelp2024.observer.jackpot;

public interface MachineRepository {
    void save(Machine machine);

    Machine findById(long machineId);
}
