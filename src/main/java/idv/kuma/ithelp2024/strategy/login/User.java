package idv.kuma.ithelp2024.strategy.login;

import lombok.Data;

@Data
public class User {
    private final long id;
    private final String email;
    private final String invitationCode;

    private Wallet wallet = new Wallet();

    private User(long id, String email, String invitationCode, Wallet wallet) {
        this.id = id;
        this.email = email;
        this.invitationCode = invitationCode;
        this.wallet = wallet;
    }

    public static User create(long id, String email, String invitationCode) {
        Wallet wallet = new Wallet();
        return new User(id, email, invitationCode, wallet);
    }

}
