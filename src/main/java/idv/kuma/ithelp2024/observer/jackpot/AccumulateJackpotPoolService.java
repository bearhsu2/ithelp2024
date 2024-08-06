package idv.kuma.ithelp2024.observer.jackpot;

import java.util.Optional;

public class AccumulateJackpotPoolService {

    private final JackpotPoolRepository jackpotPoolRepository;
    private final JackpotPoolSettingCreator jackpotPoolSettingCreator;

    private final BigScreenObserver bigScreenObserver;
    private final MachineObserver machineObserver;

    public AccumulateJackpotPoolService(MachineRepository machineRepository,
                                        JackpotPoolRepository jackpotPoolRepository,
                                        JackpotPoolSettingCreator jackpotPoolSettingCreator,
                                        BigScreenController bigScreenNotifier) {


        this.jackpotPoolRepository = jackpotPoolRepository;
        this.jackpotPoolSettingCreator = jackpotPoolSettingCreator;

        this.machineObserver = new MachineObserver(machineRepository);
        this.bigScreenObserver = new BigScreenObserver(bigScreenNotifier);
    }

    public void accumulate(long userId, long machineId, long betAmountCent) {

        Machine machine = machineObserver.machineRepository.findById(machineId);

        JackpotPool jackpotPool = jackpotPoolRepository.findById(machine.getJackpotPoolId());

        Optional<JackpotHit> jackpotHitOpt = jackpotPool.accumulate(betAmountCent, jackpotPoolSettingCreator::getNext);

        jackpotPoolRepository.save(jackpotPool);

        jackpotHitOpt.ifPresent(jackpotHit -> {

                    JackpotHitEvent jackpotHitEvent = new JackpotHitEvent(jackpotHit, userId, machine.getMachineId());


                    bigScreenObserver.notify(jackpotHitEvent);
                    machineObserver.notify(jackpotHitEvent);


                    // (will do) send prize and user to risk management department
                }
        );
    }

    private void notifyMachine(JackpotHitEvent jackpotHitEvent) {

        // send prize to machine


        machineObserver.notify(jackpotHitEvent);
    }


}
