package idv.kuma.ithelp2024.observer.jackpot;

import lombok.Data;

@Data
public class JackpotPool {
    private long id;
    private long contributionRateHundredThousandth;
    private long amountHundredThousandth;
    private long prizeCent;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setContributionRateHundredThousandth(long contributionRateHundredThousandth) {
        this.contributionRateHundredThousandth = contributionRateHundredThousandth;
    }

    public long getContributionRateHundredThousandth() {
        return contributionRateHundredThousandth;
    }

    public void setAmountHundredThousandth(long amountHundredThousandth) {
        this.amountHundredThousandth = amountHundredThousandth;
    }

    public long getAmountHundredThousandth() {
        return amountHundredThousandth;
    }

    public void setPrizeCent(long prizeCent) {
        this.prizeCent = prizeCent;
    }

    public long getPrizeCent() {
        return prizeCent;
    }
}
