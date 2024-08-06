package idv.kuma.ithelp2024.observer.jackpot;

import java.util.List;
import java.util.Optional;

public class AccumulateJackpotPoolService {

    private final JackpotPoolRepository jackpotPoolRepository;
    private final JackpotPoolSettingCreator jackpotPoolSettingCreator;

    private final BigScreenObserver bigScreenObserver;
    private final List<JackpotHitObserver> observers;
    private final MachineObserver machineObserver;

    public AccumulateJackpotPoolService(JackpotPoolRepository jackpotPoolRepository,
                                        JackpotPoolSettingCreator jackpotPoolSettingCreator,
                                        MachineObserver machineObserver,
                                        BigScreenObserver bigScreenObserver,
                                        List<JackpotHitObserver> observers) {


        this.jackpotPoolRepository = jackpotPoolRepository;
        this.jackpotPoolSettingCreator = jackpotPoolSettingCreator;

        this.machineObserver = machineObserver;
        this.bigScreenObserver = bigScreenObserver;
        this.observers = observers;
    }

    public void accumulate(long userId, long machineId, long betAmountCent) {

        Machine machine = machineObserver.machineRepository.findById(machineId);

        JackpotPool jackpotPool = jackpotPoolRepository.findById(machine.getJackpotPoolId());

        Optional<JackpotHit> jackpotHitOpt = jackpotPool.accumulate(betAmountCent, jackpotPoolSettingCreator::getNext);

        jackpotPoolRepository.save(jackpotPool);

        jackpotHitOpt.ifPresent(jackpotHit -> {

                    JackpotHitEvent jackpotHitEvent = new JackpotHitEvent(jackpotHit, userId, machine.getMachineId());

                    for (JackpotHitObserver observer : observers) {
                        observer.notify(jackpotHitEvent);
                    }

//                    bigScreenObserver.notify(jackpotHitEvent);
//                    machineObserver.notify(jackpotHitEvent);


                    // (will do) send prize and user to risk management department
                }
        );
    }

    private void notifyMachine(JackpotHitEvent jackpotHitEvent) {

        // send prize to machine


        machineObserver.notify(jackpotHitEvent);
    }


}
