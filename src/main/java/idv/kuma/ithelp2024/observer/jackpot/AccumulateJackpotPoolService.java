package idv.kuma.ithelp2024.observer.jackpot;

import java.util.Optional;

public class AccumulateJackpotPoolService {

    private final MachineRepository machineRepository;
    private final JackpotPoolRepository jackpotPoolRepository;
    private final JackpotPoolSettingCreator jackpotPoolSettingCreator;
    private BigScreenController bigScreenNotifier;

    public AccumulateJackpotPoolService(MachineRepository machineRepository,
                                        JackpotPoolRepository jackpotPoolRepository,
                                        JackpotPoolSettingCreator jackpotPoolSettingCreator,
                                        BigScreenController bigScreenNotifier) {
        this.machineRepository = machineRepository;
        this.jackpotPoolRepository = jackpotPoolRepository;
        this.jackpotPoolSettingCreator = jackpotPoolSettingCreator;
        this.bigScreenNotifier = bigScreenNotifier;
    }

    public void accumulate(long userId, long machineId, long betAmountCent) {

        Machine machine = machineRepository.findById(machineId);

        JackpotPool jackpotPool = jackpotPoolRepository.findById(machine.getJackpotPoolId());

        Optional<JackpotHit> jackpotHitOpt = jackpotPool.accumulate(betAmountCent, jackpotPoolSettingCreator::getNext);

        jackpotPoolRepository.save(jackpotPool);

        jackpotHitOpt.ifPresent(jackpotHit -> {
                    // send prize and playerId to big screen
                    bigScreenNotifier.showJackpotHit(jackpotHit.getPrizeCent(), userId);

                    // send prize to machine
                    machine.distributeJackpot(jackpotHit.getPrizeCent());
                    machineRepository.save(machine);






                    // (will do) send prize and user to risk management department
                }
        );
    }
}
