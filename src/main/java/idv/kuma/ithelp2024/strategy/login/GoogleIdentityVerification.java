package idv.kuma.ithelp2024.strategy.login;

public class GoogleIdentityVerification implements IdentityVerification {
    final GoogleLoginClient googleLoginClient;

    public GoogleIdentityVerification(GoogleLoginClient googleLoginClient) {
        this.googleLoginClient = googleLoginClient;
    }

    @Override
    public LoginResultCode execute(String token, User user) {
        String email = googleLoginClient.check(token);

        if (email.equals(user.getEmail())) {
            return LoginResultCode.OK;
        } else {
            return LoginResultCode.FAIL;
        }
    }
}