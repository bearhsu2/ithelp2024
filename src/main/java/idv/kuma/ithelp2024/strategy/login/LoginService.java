package idv.kuma.ithelp2024.strategy.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private final UserRepository userRepository;
    private final GoogleLoginClient googleLoginClient;
    private final FacebookLoginClient facebookLoginClient;

    public LoginService(UserRepository userRepository, GoogleLoginClient googleLoginClient, FacebookLoginClient facebookLoginClient) {

        this.userRepository = userRepository;
        this.googleLoginClient = googleLoginClient;
        this.facebookLoginClient = facebookLoginClient;
    }


    public LoginResultCode login(LoginType loginType, long userId, String token) {

        User user = userRepository.find(userId);


        if (loginType.GOOGLE.equals(loginType)) {

            String email = googleLoginClient.check(token);

            if (email.equals(user.getEmail())) {
                return LoginResultCode.OK;
            } else {
                return LoginResultCode.FAIL;
            }
        }

        if (loginType.FACEBOOK.equals(loginType)) {

            FacebookLoginResult result = facebookLoginClient.verify(token, user.getEmail());

            if (FacebookLoginResult.SUCCESS.equals(result)) {
                return LoginResultCode.OK;
            } else {
                return LoginResultCode.FAIL;
            }
        }

        throw new RuntimeException("Unknown login type: " + loginType);


    }
}
