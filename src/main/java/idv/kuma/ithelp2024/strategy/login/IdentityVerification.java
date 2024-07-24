package idv.kuma.ithelp2024.strategy.login;

public interface IdentityVerification {
    LoginResultCode execute(String token, User user);
}
