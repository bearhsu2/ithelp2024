package idv.kuma.ithelp2024.strategy.login;

public class LoginService {
    private final UserRepository userRepository;
    private final GoogleLoginClient googleLoginClient;

    public LoginService(UserRepository userRepository, GoogleLoginClient googleLoginClient) {

        this.userRepository = userRepository;
        this.googleLoginClient = googleLoginClient;
    }

    // Google login -> GoogleLoginClient (假的)
    // Facebook login -> FacebookLoginClient (假的)

    public LoginResultCode login(long userId, String token) {

        User user = userRepository.find(userId);


        String email = googleLoginClient.check(token);

        if (email.equals(user.getEmail())) {
            return LoginResultCode.OK;
        }

        throw new RuntimeException("Not implemented yet");
    }
}
