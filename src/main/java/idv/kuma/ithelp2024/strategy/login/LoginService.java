package idv.kuma.ithelp2024.strategy.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private final UserRepository userRepository;
    private final GoogleIdentityVerification googleIdentityVerification;
    private final FacebookIdentityVerification facebookIdentityVerification;

    public LoginService(UserRepository userRepository, GoogleLoginClient googleLoginClient, FacebookLoginClient facebookLoginClient) {

        this.userRepository = userRepository;
        googleIdentityVerification = new GoogleIdentityVerification(googleLoginClient);
        facebookIdentityVerification = new FacebookIdentityVerification(facebookLoginClient);
    }


    public LoginResultCode login(LoginType loginType, long userId, String token) {

        User user = userRepository.find(userId);


        if (loginType.GOOGLE.equals(loginType)) {

            return googleIdentityVerification.execute(token, user);
        }

        if (loginType.FACEBOOK.equals(loginType)) {

            return facebookIdentityVerification.execute(token, user);
        }

        throw new RuntimeException("Unknown login type: " + loginType);


    }

    private LoginResultCode verifyFacebook(String token, User user) {

        return facebookIdentityVerification.execute(token, user);
    }


}
