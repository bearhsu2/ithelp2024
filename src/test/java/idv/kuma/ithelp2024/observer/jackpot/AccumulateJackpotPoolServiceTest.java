package idv.kuma.ithelp2024.observer.jackpot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class AccumulateJackpotPoolServiceTest {

    private JackpotPoolRepository jackpotPoolRepository = new InMemoryJackpotPoolRepository();
    private MachineRepository machineRepository = new InMemoryMachineRepository();
    private AccumulateJackpotPoolService sut = new AccumulateJackpotPoolService(machineRepository, jackpotPoolRepository);

    @Test
    void no_jackpot() {


        JackpotPool oldJackpotPool = JackpotPool.create(30678L, 35L);

        oldJackpotPool.initialize(new JackpotPoolSetting(300_000_00L, 100_000_00_00L));

        jackpotPoolRepository.save(oldJackpotPool);

        machineRepository.save(machine(207, 30678L));

        sut.accumulate(9527L, 207L, 100_00L);

        JackpotPool newJackpotPool = jackpotPoolRepository.findById(30678L);

        Assertions.assertThat(
                newJackpotPool.getAmountTenThousandth()
        ).isEqualTo(100_000_35_00L);

    }


    private Machine machine(int machineId, long jackpotPoolId) {
        return new Machine(machineId, jackpotPoolId);
    }

}