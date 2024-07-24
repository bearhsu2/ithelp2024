package idv.kuma.ithelp2024.strategy.login;

public class FacebookIdentityVerification implements IdentityVerification {
    final FacebookLoginClient facebookLoginClient;

    public FacebookIdentityVerification(FacebookLoginClient facebookLoginClient) {
        this.facebookLoginClient = facebookLoginClient;
    }

    @Override
    public LoginResultCode execute(String token, User user) {
        FacebookLoginResult result = facebookLoginClient.verify(token, user.getEmail());

        if (FacebookLoginResult.SUCCESS.equals(result)) {
            return LoginResultCode.OK;
        } else {
            return LoginResultCode.FAIL;
        }
    }
}