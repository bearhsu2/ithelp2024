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

                    JackpotHitEvent jackpotHitEvent = new JackpotHitEvent(jackpotHit, userId, machine.getMachineId());

                    notifyBigScreen(jackpotHitEvent);

                    notifyMachine(new JackpotHitEvent(jackpotHit, userId, machine.getMachineId()));


                    // (will do) send prize and user to risk management department
                }
        );
    }

    private void notifyBigScreen(JackpotHitEvent jackpotHitEvent) {
        // send prize and playerId to big screen
        bigScreenNotifier.showJackpotHit(jackpotHitEvent.jackpotHit().getPrizeCent(), jackpotHitEvent.userId());
    }

    private void notifyMachine(JackpotHitEvent jackpotHitEvent) {

        Machine byId = machineRepository.findById(jackpotHitEvent.machineId());

        // send prize to machine


        byId.distributeJackpot(jackpotHitEvent.jackpotHit().getPrizeCent());
        machineRepository.save(byId);
    }
}
