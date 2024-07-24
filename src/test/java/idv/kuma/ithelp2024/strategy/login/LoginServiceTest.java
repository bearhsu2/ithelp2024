package idv.kuma.ithelp2024.strategy.login;

import org.assertj.core.api.AbstractComparableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.ArgumentMatchers.anyString;

class LoginServiceTest {

    private GoogleLoginClient googleLoginClient = Mockito.mock(GoogleLoginClient.class);
    private DummyUserRepository userRepository = new DummyUserRepository();

    private LoginService sut = new LoginService(
            userRepository, googleLoginClient
    );
    private LoginResultCode actual;

    @Test
    void google_login_fail() {

        given_google_token_belongs_to("tommy@gmail.com");

        given_user(user(1L, "kuma@gmail.com"));

        when_login(1L, "google_token");

        Assertions.assertThat(actual).isEqualTo(LoginResultCode.FAIL);

    }

    private OngoingStubbing<String> given_google_token_belongs_to(String email) {
        return Mockito.when(googleLoginClient.check(anyString())).thenReturn(email);
    }

    private void given_user(User user) {
        userRepository.save(user);
    }

    private User user(long id, String email) {
        return new User(id, email);
    }

    private void when_login(long userId, String token) {
        actual = sut.login(userId, token);
    }

    @Test
    void facebook_login_ok() {

        given_user(user(1L, "kuma@gmail.com"));


        FacebookLoginClient facebookLoginClient = Mockito.mock(FacebookLoginClient.class);
        Mockito.when(facebookLoginClient.verify("facebook_token", "kuma@gmail.com"))
                .thenReturn(FacebookLoginResult.SUCCESS);

        when_login(1L, "facebook_token");

        then_result_is(LoginResultCode.OK);

    }

    private AbstractComparableAssert<?, LoginResultCode> then_result_is(LoginResultCode result) {
        return Assertions.assertThat(actual).isEqualTo(result);
    }

    @Test
    void google_login_ok() {

        given_google_token_belongs_to("kuma@gmail.com");

        given_user(user(1L, "kuma@gmail.com"));

        when_login(1L, "google_token");

        then_result_is(LoginResultCode.OK);

    }
}