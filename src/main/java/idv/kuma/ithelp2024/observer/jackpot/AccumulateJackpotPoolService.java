package idv.kuma.ithelp2024.observer.jackpot;

import java.util.Optional;

public class AccumulateJackpotPoolService {


    private final MachineRepository machineRepository;
    private final JackpotPoolRepository jackpotPoolRepository;
    private final JackpotPoolSettingCreator jackpotPoolSettingCreator;
    private BigScreenController bigScreenController;

    public AccumulateJackpotPoolService(MachineRepository machineRepository, JackpotPoolRepository jackpotPoolRepository, JackpotPoolSettingCreator jackpotPoolSettingCreator, BigScreenController bigScreenController) {
        this.machineRepository = machineRepository;
        this.jackpotPoolRepository = jackpotPoolRepository;
        this.jackpotPoolSettingCreator = jackpotPoolSettingCreator;
        this.bigScreenController = bigScreenController;
    }

    public void accumulate(long userId, long machineId, long betAmountCent) {

        Machine machine = machineRepository.findById(machineId);

        JackpotPool jackpotPool = jackpotPoolRepository.findById(machine.getJackpotPoolId());

        Optional<JackpotHit> jackpotHitOpt = jackpotPool.accumulate(betAmountCent, jackpotPoolSettingCreator::getNext);

        // update pool
        jackpotPoolRepository.save(jackpotPool);

        jackpotHitOpt.ifPresent(jackpotHit -> {
                    bigScreenController.showJackpotHit(jackpotHit.getPrizeCent(), userId);
                }
        );


    }

}
