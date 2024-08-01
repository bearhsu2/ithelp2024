package idv.kuma.ithelp2024.observer.jackpot;

import lombok.Data;

import java.util.Optional;
import java.util.function.Supplier;

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

    Optional<JackpotHit> accumulate(long betAmountCent, Supplier<JackpotPoolSetting> settingSupplier) {
        long contributionTenThousandth = betAmountCent * 100 * contributionRateTenThousandth / 10000;

        setAmountTenThousandth(amountTenThousandth + contributionTenThousandth);

        if (isJackpotHit()) {

            JackpotPoolSetting next = settingSupplier.get();

            JackpotHit jackpotHit = new JackpotHit(prizeCent);

            initialize(next);
            
            return Optional.of(jackpotHit);


        }

        return Optional.empty();
    }

    private boolean isJackpotHit() {
        return getAmountTenThousandth() >= getPrizeCent() * 100;
    }

    public void initialize(JackpotPoolSetting jackpotPoolSetting) {
        setAmountTenThousandth(jackpotPoolSetting.amountTenThousandth());
        setPrizeCent(jackpotPoolSetting.prizeCent());
    }
}
