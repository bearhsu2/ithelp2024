package idv.kuma.ithelp2024.strategy.login;

import lombok.Data;

@Data
public class Wallet {
    private int balance;

    public void add(int amount) {
        this.balance += amount;
    }
}
