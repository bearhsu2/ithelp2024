package idv.kuma.ithelp2024.strategy.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginServiceTest {

    @Test
    void google_login_ok() {


        LoginService sut = new LoginService();

        LoginResultCode actual = sut.login(1L, "google_token");

        Assertions.assertThat(actual).isEqualTo(LoginResultCode.OK);

    }
}