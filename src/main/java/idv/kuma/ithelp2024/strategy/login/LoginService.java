package idv.kuma.ithelp2024.strategy.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private final UserRepository userRepository;
//    private final GoogleIdentityVerification googleIdentityVerification;
//    private final FacebookIdentityVerification facebookIdentityVerification;

    private final Map<LoginType, IdentityVerification> identityVerifications = new HashMap<>();

    public LoginService(UserRepository userRepository, GoogleIdentityVerification googleIdentityVerification, FacebookIdentityVerification facebookIdentityVerification) {

        this.userRepository = userRepository;

//        this.googleIdentityVerification = googleIdentityVerification;
//        this.facebookIdentityVerification = facebookIdentityVerification;

        this.identityVerifications.put(LoginType.GOOGLE, googleIdentityVerification);
        this.identityVerifications.put(LoginType.FACEBOOK, facebookIdentityVerification);
    }


    public LoginResultCode login(LoginType loginType, long userId, String token) {

        User user = userRepository.find(userId);


        IdentityVerification identityVerification = identityVerifications.get(loginType);

        if (identityVerification == null) {
            throw new RuntimeException("Unknown login type: " + loginType);
        }

        return identityVerification.execute(token, user);


    }

}
