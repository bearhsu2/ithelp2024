package idv.kuma.ithelp2024.strategy.login;

import org.assertj.core.api.AbstractComparableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyString;

class LoginServiceTest {

    private GoogleLoginClient googleLoginClient = Mockito.mock(GoogleLoginClient.class);

    private DummyUserRepository userRepository = new DummyUserRepository();
    private LoginResultCode actual;
    private FacebookLoginClient facebookLoginClient = Mockito.mock(FacebookLoginClient.class);
    private HashMap<LoginType, IdentityVerification> identityVerifications = new HashMap<>();

    @BeforeEach
    void setUp() {
        identityVerifications.put(LoginType.GOOGLE, new GoogleIdentityVerification(googleLoginClient));
        identityVerifications.put(LoginType.FACEBOOK, new FacebookIdentityVerification(facebookLoginClient));
    }

    private LoginService sut = new LoginService(
            userRepository, identityVerifications
    );

    @Test
    void google_login_fail() {

        given_google_token_belongs_to("tommy@gmail.com");

        given_user(user(1L, "kuma@gmail.com"));

        when_login(LoginType.GOOGLE, 1L, "google_token");

        Assertions.assertThat(actual).isEqualTo(LoginResultCode.FAIL);

    }

    private OngoingStubbing<String> given_google_token_belongs_to(String email) {
        return Mockito.when(googleLoginClient.check(anyString())).thenReturn(email);
    }

    private void given_user(User user) {
        userRepository.save(user);
    }

    private User user(long id, String email) {
        return User.create(id, email, "ABCD001");
    }

    private void when_login(LoginType loginType, long userId, String token) {
        actual = sut.login(loginType, userId, token);
    }

    @Test
    void facebook_login_fail() {

        given_user(user(1L, "kuma@gmail.com"));


        Mockito.when(facebookLoginClient.verify("facebook_token", "kuma@gmail.com"))
                .thenReturn(FacebookLoginResult.UNSUCCESSFUL);

        when_login(LoginType.FACEBOOK, 1L, "facebook_token");

        then_result_is(LoginResultCode.FAIL);

    }

    private AbstractComparableAssert<?, LoginResultCode> then_result_is(LoginResultCode result) {
        return Assertions.assertThat(actual).isEqualTo(result);
    }

    @Test
    void facebook_login_ok() {

        given_user(user(1L, "kuma@gmail.com"));


        Mockito.when(facebookLoginClient.verify("facebook_token", "kuma@gmail.com"))
                .thenReturn(FacebookLoginResult.SUCCESS);

        when_login(LoginType.FACEBOOK, 1L, "facebook_token");

        then_result_is(LoginResultCode.OK);

    }

    @Test
    void google_login_ok() {

        given_google_token_belongs_to("kuma@gmail.com");

        given_user(user(1L, "kuma@gmail.com"));

        when_login(LoginType.GOOGLE, 1L, "google_token");

        then_result_is(LoginResultCode.OK);

    }
}