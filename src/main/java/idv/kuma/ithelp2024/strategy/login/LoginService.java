package idv.kuma.ithelp2024.strategy.login;

public class LoginService {

    // Google login -> GoogleLoginClient (假的)
    // Facebook login -> FacebookLoginClient (假的)

    public LoginResultCode login(long userId, String token) {
        return LoginResultCode.OK;
    }
}
