package idv.kuma.ithelp2024.strategy.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static idv.kuma.ithelp2024.strategy.login.LoginType.GOOGLE;
import static org.mockito.ArgumentMatchers.anyString;

class LoginServiceTest {

    @Test
    void google_login() {

        GoogleLoginClient googleLoginClient = Mockito.mock(GoogleLoginClient.class);
        Mockito.when(googleLoginClient.check(anyString())).thenReturn("kukumama@gmail.com");

        UserRepository userRepository = new DummyUserRepository();
        userRepository.add(new User(1L, "kukumama@gmail.com"));

        LoginService loginService = new LoginService(googleLoginClient, userRepository);

        LoginResultCode loginResultCode = loginService.login(GOOGLE, 1L, "google_login_token");

        Assertions.assertThat(loginResultCode).isEqualTo(LoginResultCode.OK);

    }
}