package idv.kuma.ithelp2024.strategy.login;

public class FacebookIdentityVerification {
    final FacebookLoginClient facebookLoginClient;

    public FacebookIdentityVerification(FacebookLoginClient facebookLoginClient) {
        this.facebookLoginClient = facebookLoginClient;
    }

    LoginResultCode execute(String token, User user) {
        FacebookLoginResult result = facebookLoginClient.verify(token, user.getEmail());

        if (FacebookLoginResult.SUCCESS.equals(result)) {
            return LoginResultCode.OK;
        } else {
            return LoginResultCode.FAIL;
        }
    }
}