package idv.kuma.ithelp2024.observer.jackpot;

public class MachineObserver implements JackpotHitObserver {
    final MachineRepository machineRepository;

    public MachineObserver(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public void notify(JackpotHitEvent jackpotHitEvent) {
        Machine byId = machineRepository.findById(jackpotHitEvent.machineId());
        // send prize to machine
        byId.distributeJackpot(jackpotHitEvent.jackpotHit().getPrizeCent());
        machineRepository.save(byId);
    }
}