package idv.kuma.ithelp2024.observer.jackpot;

import java.util.HashMap;
import java.util.Map;

public class InMemoryMachineRepository implements MachineRepository {

    Map<Long, Machine> machines = new HashMap<>();

    @Override
    public void save(Machine machine) {
        machines.put(machine.getMachineId(), machine);
    }

    @Override
    public Machine findById(long machineId) {
        return machines.get(machineId);
    }
}
