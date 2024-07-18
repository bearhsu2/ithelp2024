package idv.kuma.ithelp2024.strategy.login;

public class LoginService {

    private final GoogleLoginClient googleLoginClient;
    private final UserRepository userRepository;

    private FacebookLoginClient facebookLoginClient;

    public LoginService(GoogleLoginClient googleLoginClient, UserRepository userRepository, FacebookLoginClient facebookLoginClient) {
        this.googleLoginClient = googleLoginClient;
        this.userRepository = userRepository;
        this.facebookLoginClient = facebookLoginClient;
    }

    public LoginResultCode login(LoginType loginType, long userId, String token) {

        User user = userRepository.find(userId);

        if (loginType.equals(LoginType.GOOGLE)) {

            String email = googleLoginClient.check(token);
            if (user.getEmail().equals(email)) {
                return LoginResultCode.OK;
            } else {
                return LoginResultCode.FAILED;
            }
        }

        if (loginType.equals(LoginType.FACEBOOK)) {

            FacebookLoginResult facebookLoginResult = facebookLoginClient.verify(token, user.getEmail());
            if (facebookLoginResult.equals(FacebookLoginResult.SUCCESSFUL)) {
                return LoginResultCode.OK;
            } else {
                return LoginResultCode.FAILED;
            }

        } else

            throw new RuntimeException("Unknown Login Type: " + loginType);

    }
}
