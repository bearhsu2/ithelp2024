package idv.kuma.ithelp2024.strategy.login;

public class LoginService {

    private final GoogleLoginClient googleLoginClient;
    private final UserRepository userRepository;

    public LoginService(GoogleLoginClient googleLoginClient, UserRepository userRepository) {
        this.googleLoginClient = googleLoginClient;
        this.userRepository = userRepository;
    }

    public LoginResultCode login(LoginType loginType, long userId, String token) {
        return LoginResultCode.OK;
    }
}
