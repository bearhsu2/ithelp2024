package idv.kuma.ithelp2024.strategy.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;

class LoginServiceTest {

    @Test
    void google_login_ok() {

        GoogleLoginClient googleLoginClient = Mockito.mock(GoogleLoginClient.class);
        Mockito.when(googleLoginClient.check(anyString())).thenReturn("kuma@gmail.com");

        UserRepository userRepository = new DummyUserRepository();
        User user = new User(1L, "kuma@gmail.com");
        userRepository.save(user);

        LoginService sut = new LoginService(
                userRepository, googleLoginClient
        );

        LoginResultCode actual = sut.login(1L, "google_token");

        Assertions.assertThat(actual).isEqualTo(LoginResultCode.OK);

    }
}