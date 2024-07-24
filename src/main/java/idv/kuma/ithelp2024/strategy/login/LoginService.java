package idv.kuma.ithelp2024.strategy.login;

public class LoginService {
    private final UserRepository userRepository;
    private final GoogleLoginClient googleLoginClient;

    public LoginService(UserRepository userRepository, GoogleLoginClient googleLoginClient) {

        this.userRepository = userRepository;
        this.googleLoginClient = googleLoginClient;
    }


    public LoginResultCode login(long userId, String token) {

        User user = userRepository.find(userId);


        String email = googleLoginClient.check(token);

        if (email.equals(user.getEmail())) {
            return LoginResultCode.OK;
        } else {
            return LoginResultCode.FAIL;
        }

    }
}
