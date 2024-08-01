package idv.kuma.ithelp2024.observer.jackpot;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Machine {
    private final long machineId;
    private final long jackpotPoolId;
    private final List<DistributeRecord> distributeRecord = new ArrayList<>();

    public Machine(long machineId, long jackpotPoolId) {
        this.machineId = machineId;
        this.jackpotPoolId = jackpotPoolId;
    }

    public void distributeJackpot(long prizeCent) {
        this.distributeRecord.add(new DistributeRecord(prizeCent));
    }
}
