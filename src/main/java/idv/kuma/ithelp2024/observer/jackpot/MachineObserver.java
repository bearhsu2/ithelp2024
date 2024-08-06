package idv.kuma.ithelp2024.observer.jackpot;

public class MachineObserver {
    final MachineRepository machineRepository;

    public MachineObserver(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    void notifyMachine(JackpotHitEvent jackpotHitEvent) {
        Machine byId = machineRepository.findById(jackpotHitEvent.machineId());
        // send prize to machine
        byId.distributeJackpot(jackpotHitEvent.jackpotHit().getPrizeCent());
        machineRepository.save(byId);
    }
}