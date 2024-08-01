package idv.kuma.ithelp2024.observer.jackpot;

public class AccumulateJackpotPoolService {


    private final MachineRepository machineRepository;
    private final JackpotPoolRepository jackpotPoolRepository;
    private final JackpotPoolSettingCreator jackpotPoolSettingCreator;

    public AccumulateJackpotPoolService(MachineRepository machineRepository, JackpotPoolRepository jackpotPoolRepository, JackpotPoolSettingCreator jackpotPoolSettingCreator) {
        this.machineRepository = machineRepository;
        this.jackpotPoolRepository = jackpotPoolRepository;
        this.jackpotPoolSettingCreator = jackpotPoolSettingCreator;
    }

    public void accumulate(long userId, long machineId, long betAmountCent) {

        Machine machine = machineRepository.findById(machineId);

        JackpotPool jackpotPool = jackpotPoolRepository.findById(machine.getJackpotPoolId());

        jackpotPool.accumulate(betAmountCent, jackpotPoolSettingCreator::getNext);

        // update pool
        jackpotPoolRepository.save(jackpotPool);

    }

}
