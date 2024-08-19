package idv.kuma.ithelp2024.responsibility.chain.reference;

import lombok.Getter;

@Getter
public class DrawBonusRecord {
    private final long userId;
    private final String bonusCode;

    public DrawBonusRecord(long userId, String bonusCode) {

        this.userId = userId;
        this.bonusCode = bonusCode;
    }

    boolean matches(long userId, String bonusCode) {
        return this.userId == userId && this.bonusCode.equals(bonusCode);
    }
}
