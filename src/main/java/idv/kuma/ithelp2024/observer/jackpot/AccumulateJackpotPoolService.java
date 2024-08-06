package idv.kuma.ithelp2024.observer.jackpot;

import java.util.List;
import java.util.Optional;

public class AccumulateJackpotPoolService {

    private final JackpotPoolRepository jackpotPoolRepository;
    private final JackpotPoolSettingCreator jackpotPoolSettingCreator;
    private final MachineRepository machineRepository;

    private final List<JackpotHitObserver> observers;

    public AccumulateJackpotPoolService(JackpotPoolRepository jackpotPoolRepository,
                                        JackpotPoolSettingCreator jackpotPoolSettingCreator,
                                        MachineRepository machineRepository,
                                        List<JackpotHitObserver> observers) {


        this.jackpotPoolRepository = jackpotPoolRepository;
        this.jackpotPoolSettingCreator = jackpotPoolSettingCreator;
        this.machineRepository = machineRepository;

        this.observers = observers;
    }

    public void accumulate(long userId, long machineId, long betAmountCent) {

        Machine machine = machineRepository.findById(machineId);

        JackpotPool jackpotPool = jackpotPoolRepository.findById(machine.getJackpotPoolId());

        Optional<JackpotHit> jackpotHitOpt = jackpotPool.accumulate(betAmountCent, jackpotPoolSettingCreator::getNext);

        jackpotPoolRepository.save(jackpotPool);

        jackpotHitOpt.ifPresent(jackpotHit -> {

                    JackpotHitEvent jackpotHitEvent = new JackpotHitEvent(jackpotHit, userId, machine.getMachineId());

                    notifyObservers(jackpotHitEvent);

                    // (will do) send prize and user to risk management department
                }
        );
    }

    private void notifyObservers(JackpotHitEvent jackpotHitEvent) {
        for (JackpotHitObserver observer : observers) {
            observer.notify(jackpotHitEvent);
        }
    }

}
