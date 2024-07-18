package idv.kuma.ithelp2024.strategy.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static idv.kuma.ithelp2024.strategy.login.LoginType.GOOGLE;
import static org.mockito.ArgumentMatchers.anyString;

class LoginServiceTest {

    private GoogleLoginClient googleLoginClient = Mockito.mock(GoogleLoginClient.class);
    private UserRepository userRepository = new DummyUserRepository();
    private LoginService loginService = new LoginService(googleLoginClient, userRepository);
    private LoginResultCode loginResultCode;

    @Test
    void google_login_ok() {

        Mockito.when(googleLoginClient.check(anyString())).thenReturn("kukumama@gmail.com");

        userRepository.add(new User(1L, "kukumama@gmail.com"));

        loginResultCode = loginService.login(GOOGLE, 1L, "google_login_token");

        Assertions.assertThat(loginResultCode).isEqualTo(LoginResultCode.OK);

    }

    @Test
    void google_login_failed() {

        Mockito.when(googleLoginClient.check(anyString())).thenReturn("micky_mouse@gmail.com");

        userRepository.add(new User(1L, "kukumama@gmail.com"));

        loginResultCode = loginService.login(GOOGLE, 1L, "google_login_token");

        Assertions.assertThat(loginResultCode).isEqualTo(LoginResultCode.FAILED);

    }
}