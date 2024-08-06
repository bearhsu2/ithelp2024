package idv.kuma.ithelp2024.observer.jackpot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

@Disabled
class AccumulateJackpotPoolServiceTest {

    private final JackpotPoolRepository jackpotPoolRepository = new InMemoryJackpotPoolRepository();
    private final MachineRepository machineRepository = new InMemoryMachineRepository();
    private final FakeJackpotPoolSettingCreator jackpotPoolSettingCreator = new FakeJackpotPoolSettingCreator();
    private final FakeBigScreenController bigScreenController = new FakeBigScreenController();

    private List<JackpotHitObserver> observers = List.of(
            new BigScreenObserver(bigScreenController),
            new MachineObserver(machineRepository)
    );



    private final AccumulateJackpotPoolService sut = new AccumulateJackpotPoolService(
            jackpotPoolRepository,
            jackpotPoolSettingCreator,
            new MachineObserver(machineRepository),
            new BigScreenObserver(bigScreenController),
            observers
    );

    private Machine machine;

    @Test
    void jackpot() {

        given_jackpot_pool(JackpotPool.create(30678L, 35L),
                setting(300_000_00L, 299_999_99_00L)
        );

        given_machine(machine(207, 30678L));

        given_next_created_setting(setting(400_000_00L, 150_000_00_00L));

        when_accumulate(9527L, 207L, 100_00L);

        then_pool_amount_should_be(150_000_00_00L);

        then_big_screen_should_notify(300_000_00L, 9527L);

        then_machine_should_distribute_prize(300_000_00L);

    }

    private void given_jackpot_pool(JackpotPool oldJackpotPool, JackpotPoolSetting setting) {

        oldJackpotPool.initialize(setting);

        jackpotPoolRepository.save(oldJackpotPool);
    }

    private JackpotPoolSetting setting(long prizeCent, long amountTenThousandth) {
        return new JackpotPoolSetting(prizeCent, amountTenThousandth);
    }

    private void given_machine(Machine machine) {
        this.machine = machine;

        machineRepository.save(this.machine);
    }

    private Machine machine(int machineId, long jackpotPoolId) {
        return new Machine(machineId, jackpotPoolId);
    }

    private void given_next_created_setting(JackpotPoolSetting setting) {
        jackpotPoolSettingCreator.setNext(
                setting
        );
    }

    private void when_accumulate(long userId, long machineId, long betAmountCent) {
        sut.accumulate(userId, machineId, betAmountCent);
    }

    private void then_pool_amount_should_be(long expected) {
        JackpotPool newJackpotPool = jackpotPoolRepository.findById(30678L);

        Assertions.assertThat(
                newJackpotPool.getAmountTenThousandth()
        ).isEqualTo(expected);
    }

    private void then_big_screen_should_notify(long prizeCent, long userId) {
        Assertions.assertThat(
                bigScreenController.screenRecords.get(0)
        ).isEqualTo(new ScreenRecord(prizeCent, userId));
    }

    private void then_machine_should_distribute_prize(long prizeCent) {
        Assertions.assertThat(
                machine.getDistributeRecord().get(0)
        ).isEqualTo(new DistributeRecord(prizeCent));
    }

    @Test
    void no_jackpot() {


        given_jackpot_pool(JackpotPool.create(30678L, 35L),
                setting(300_000_00L, 100_000_00_00L));

        given_machine(machine(207, 30678L));

        when_accumulate(9527L, 207L, 100_00L);

        then_pool_amount_should_be(100_000_35_00L);

        then_NEVER_notified_big_screen();

        then_NEVER_distributed_prize();


    }

    private void then_NEVER_notified_big_screen() {
        Assertions.assertThat(
                bigScreenController.screenRecords
        ).isEmpty();
    }

    private void then_NEVER_distributed_prize() {
        Assertions.assertThat(
                machine.getDistributeRecord()
        ).isEmpty();
    }

}