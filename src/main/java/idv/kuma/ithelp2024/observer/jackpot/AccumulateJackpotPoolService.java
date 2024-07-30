package idv.kuma.ithelp2024.observer.jackpot;

public class AccumulateJackpotPoolService {

    private final MachineRepository machineRepository;
    private final JackpotPoolRepository jackpotPoolRepository;

    public AccumulateJackpotPoolService(MachineRepository machineRepository, JackpotPoolRepository jackpotPoolRepository) {
        this.machineRepository = machineRepository;
        this.jackpotPoolRepository = jackpotPoolRepository;
    }

    public void accumulate(long userId, long machineId, long betAmountCent) {

        Machine machine = machineRepository.findById(machineId);

        JackpotPool jackpotPool = jackpotPoolRepository.findById(machine.getJackpotPoolId());

        jackpotPool.accumulate(betAmountCent);

        jackpotPoolRepository.save(jackpotPool);

    }

}
