package idv.kuma.ithelp2024.observer.jackpot;

public class AccumulateJackpotPoolService {

    // 都要做：update pool
    // 有中：send prize and playerId to big screen
    //      send prize to machine
    //      (will do) send prize and user to risk management department


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

        jackpotPool.accumulate(betAmountCent);

        if (jackpotPool.isJackpotHit()) {
            JackpotPoolSetting next = jackpotPoolSettingCreator.getNext();
            jackpotPool.initialize(next);
        }

        jackpotPoolRepository.save(jackpotPool);

    }

}
