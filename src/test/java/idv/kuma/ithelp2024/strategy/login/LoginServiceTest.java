package idv.kuma.ithelp2024.strategy.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static idv.kuma.ithelp2024.strategy.login.LoginType.GOOGLE;

class LoginServiceTest {

    private GoogleLoginClient googleLoginClient = Mockito.mock(GoogleLoginClient.class);
    private UserRepository userRepository = new DummyUserRepository();
    private LoginService loginService = new LoginService(googleLoginClient, userRepository);
    private LoginResultCode loginResultCode;

    @Test
    void google_login_ok() {

        given_google_token_exists("google_login_token", "kukumama@gmail.com");

        given_user(1L, "kukumama@gmail.com");

        when_login(GOOGLE, 1L, "google_login_token");

        Assertions.assertThat(loginResultCode).isEqualTo(LoginResultCode.OK);

    }

    private OngoingStubbing<String> given_google_token_exists(String token, String email) {
        return Mockito.when(googleLoginClient.check(token)).thenReturn(email);
    }

    private void given_user(long userId, String email) {
        userRepository.add(new User(userId, email));
    }

    private void when_login(LoginType loginType, long userId, String token) {
        loginResultCode = loginService.login(loginType, userId, token);
    }

    @Test
    void google_login_failed() {

        given_google_token_exists("google_login_token", "micky_mouse@gmail.com");

        given_user(1L, "kukumama@gmail.com");

        when_login(GOOGLE, 1L, "google_login_token");

        Assertions.assertThat(loginResultCode).isEqualTo(LoginResultCode.FAILED);

    }
}