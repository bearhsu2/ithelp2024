package idv.kuma.ithelp2024.strategy.login;

public class GoogleIdentityVerification {
    final GoogleLoginClient googleLoginClient;

    public GoogleIdentityVerification(GoogleLoginClient googleLoginClient) {
        this.googleLoginClient = googleLoginClient;
    }

    LoginResultCode execute(String token, User user) {
        String email = googleLoginClient.check(token);

        if (email.equals(user.getEmail())) {
            return LoginResultCode.OK;
        } else {
            return LoginResultCode.FAIL;
        }
    }
}