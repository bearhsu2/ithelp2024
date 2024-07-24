package idv.kuma.ithelp2024.strategy.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.ArgumentMatchers.anyString;

class LoginServiceTest {

    private GoogleLoginClient googleLoginClient = Mockito.mock(GoogleLoginClient.class);

    @Test
    void google_login_fail() {

        given_google_token_belongs_to("tommy@gmail.com");

        UserRepository userRepository = new DummyUserRepository();
        User user = new User(1L, "kuma@gmail.com");
        userRepository.save(user);

        LoginService sut = new LoginService(
                userRepository, googleLoginClient
        );

        LoginResultCode actual = sut.login(1L, "google_token");

        Assertions.assertThat(actual).isEqualTo(LoginResultCode.FAIL);

    }

    private OngoingStubbing<String> given_google_token_belongs_to(String email) {
        return Mockito.when(googleLoginClient.check(anyString())).thenReturn(email);
    }

    @Test
    void google_login_ok() {

        given_google_token_belongs_to("kuma@gmail.com");

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