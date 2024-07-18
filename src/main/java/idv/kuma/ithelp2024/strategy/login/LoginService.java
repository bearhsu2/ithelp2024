package idv.kuma.ithelp2024.strategy.login;

public class LoginService {

    private final GoogleLoginClient googleLoginClient;
    private final UserRepository userRepository;

    public LoginService(GoogleLoginClient googleLoginClient, UserRepository userRepository) {
        this.googleLoginClient = googleLoginClient;
        this.userRepository = userRepository;
    }

    public LoginResultCode login(LoginType loginType, long userId, String token) {

        String email = googleLoginClient.check(token);
        User user = userRepository.find(userId);
        if (user.getEmail().equals(email)) {
            return LoginResultCode.OK;
        } else {
            return LoginResultCode.FAILED;
        }

    }
}
