package idv.kuma.ithelp2024.observer.jackpot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class AccumulateJackpotPoolServiceTest {

    private JackpotPoolRepository jackpotPoolRepository = new InMemoryJackpotPoolRepository();
    private MachineRepository machineRepository = new InMemoryMachineRepository();
    private FakeJackpotPoolSettingCreator jackpotPoolSettingCreator = new FakeJackpotPoolSettingCreator();
    private AccumulateJackpotPoolService sut = new AccumulateJackpotPoolService(machineRepository, jackpotPoolRepository, jackpotPoolSettingCreator);

    @Test
    void jackpot() {

        JackpotPool oldJackpotPool = JackpotPool.create(30678L, 35L);

        oldJackpotPool.initialize(setting(300_000_00L, 299_999_99_00L));

        jackpotPoolRepository.save(oldJackpotPool);

        machineRepository.save(machine(207, 30678L));


        jackpotPoolSettingCreator.setNext(
                setting(400_000_00L, 150_000_00_00L)
        );

        sut.accumulate(9527L, 207L, 100_00L);

        JackpotPool newJackpotPool = jackpotPoolRepository.findById(30678L);

        Assertions.assertThat(
                newJackpotPool.getAmountTenThousandth()
        ).isEqualTo(150_000_00_00L);

    }

    private JackpotPoolSetting setting(long prizeCent, long amountTenThousandth) {
        return new JackpotPoolSetting(prizeCent, amountTenThousandth);
    }

    private Machine machine(int machineId, long jackpotPoolId) {
        return new Machine(machineId, jackpotPoolId);
    }

    @Test
    void no_jackpot() {


        JackpotPool oldJackpotPool = JackpotPool.create(30678L, 35L);

        oldJackpotPool.initialize(setting(300_000_00L, 100_000_00_00L));

        jackpotPoolRepository.save(oldJackpotPool);

        machineRepository.save(machine(207, 30678L));

        sut.accumulate(9527L, 207L, 100_00L);

        JackpotPool newJackpotPool = jackpotPoolRepository.findById(30678L);

        Assertions.assertThat(
                newJackpotPool.getAmountTenThousandth()
        ).isEqualTo(100_000_35_00L);

    }

}