package idv.kuma.ithelp2024.observer.jackpot;

import lombok.Data;

@Data
public class JackpotPool {
    private long id;
    private long contributionRateTenThousandth;
    private long amountTenThousandth;
    private long prizeCent;


    public static JackpotPool create(long id, long contributionRateTenThousandth) {
        JackpotPool oldJackpotPool = new JackpotPool();
        oldJackpotPool.setId(id);
        oldJackpotPool.setContributionRateTenThousandth(contributionRateTenThousandth);
        return oldJackpotPool;
    }

    public void initialize(JackpotPoolSetting jackpotPoolSetting) {
        setAmountTenThousandth(jackpotPoolSetting.amountTenThousandth());
        setPrizeCent(jackpotPoolSetting.prizeCent());
    }

    void accumulate(long betAmountCent) {

        long contributionTenThousandth = betAmountCent * 100 * this.contributionRateTenThousandth / 10000;

        setAmountTenThousandth(this.amountTenThousandth + contributionTenThousandth);
    }

    boolean isJackpotHit() {
        return getAmountTenThousandth() >= getPrizeCent() * 100;
    }
}
