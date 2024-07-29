package idv.kuma.ithelp2024.observer.jackpot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccumulateJackpotPoolServiceTest {

    @Test
    void no_jackpot() {

        AccumulateJackpotPoolService sut = new AccumulateJackpotPoolService();

        JackpotPool jackpotPool = new JackpotPool();
        jackpotPool.setId(3345678L);
        jackpotPool.setContributionRateHundredThousandth(35L);
        jackpotPool.setAmountHundredThousandth(100_000_00_00L);
        jackpotPool.setPrizeCent(300_000_00L);

        JackpotPoolRepository jackpotPoolRepository = new JackpotPoolRepository();
        jackpotPoolRepository.save(jackpotPool);

        sut.accumulate(9527L, 100_00L);

        Assertions.assertThat(
                jackpotPool.getAmountHundredThousandth()
        ).isEqualTo(100_000_35_00L);
        // update new pool to big screen
        // update prize to big screen
        // update prize to machine


    }
}