package idv.kuma.ithelp2024.observer.jackpot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class AccumulateJackpotPoolServiceTest {

    @Test
    void no_jackpot() {

        AccumulateJackpotPoolService sut = new AccumulateJackpotPoolService();

        JackpotPool oldJackpotPool = new JackpotPool();
        oldJackpotPool.setId(30678L);
        oldJackpotPool.setContributionRateHundredThousandth(35L);
        oldJackpotPool.setAmountHundredThousandth(100_000_00_00L);
        oldJackpotPool.setPrizeCent(300_000_00L);

        JackpotPoolRepository jackpotPoolRepository = new InMemoryJackpotPoolRepository();
        jackpotPoolRepository.save(oldJackpotPool);

        sut.accumulate(9527L, 207L, 100_00L);


        JackpotPool newJackpotPool = jackpotPoolRepository.findById(3345678L);
        Assertions.assertThat(
                newJackpotPool.getAmountHundredThousandth()
        ).isEqualTo(100_000_35_00L);

        // 都要做：update pool
        // 有中：send prize and machine to big screen
        //      send prize to machine
        //      (will do) send prize and user to risk management department


    }
}