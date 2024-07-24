package idv.kuma.ithelp2024.strategy.login;

import java.util.Map;

public class LoginService {

    private final Map<LoginType, IdentityVerification> identityVerifications;

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository, Map<LoginType, IdentityVerification> identityVerifications) {
        this.userRepository = userRepository;
        this.identityVerifications = identityVerifications;
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
