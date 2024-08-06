package idv.kuma.ithelp2024.observer.jackpot;

import java.util.Optional;

public class AccumulateJackpotPoolService {

    private final MachineRepository machineRepository;
    private final JackpotPoolRepository jackpotPoolRepository;
    private final JackpotPoolSettingCreator jackpotPoolSettingCreator;
    private final BigScreenObserver bigScreenObserver;

    public AccumulateJackpotPoolService(MachineRepository machineRepository,
                                        JackpotPoolRepository jackpotPoolRepository,
                                        JackpotPoolSettingCreator jackpotPoolSettingCreator,
                                        BigScreenController bigScreenNotifier) {


        this.machineRepository = machineRepository;
        this.jackpotPoolRepository = jackpotPoolRepository;
        this.jackpotPoolSettingCreator = jackpotPoolSettingCreator;

        bigScreenObserver = new BigScreenObserver(bigScreenNotifier);
    }

    public void accumulate(long userId, long machineId, long betAmountCent) {

        Machine machine = machineRepository.findById(machineId);

        JackpotPool jackpotPool = jackpotPoolRepository.findById(machine.getJackpotPoolId());

        Optional<JackpotHit> jackpotHitOpt = jackpotPool.accumulate(betAmountCent, jackpotPoolSettingCreator::getNext);

        jackpotPoolRepository.save(jackpotPool);

        jackpotHitOpt.ifPresent(jackpotHit -> {

                    JackpotHitEvent jackpotHitEvent = new JackpotHitEvent(jackpotHit, userId, machine.getMachineId());


                    bigScreenObserver.notifyBigScreen(jackpotHitEvent);

                    notifyMachine(jackpotHitEvent);


                    // (will do) send prize and user to risk management department
                }
        );
    }

    private void notifyMachine(JackpotHitEvent jackpotHitEvent) {

        Machine byId = machineRepository.findById(jackpotHitEvent.machineId());

        // send prize to machine


        byId.distributeJackpot(jackpotHitEvent.jackpotHit().getPrizeCent());
        machineRepository.save(byId);
    }

 
}
