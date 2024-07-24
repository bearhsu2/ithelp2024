package idv.kuma.ithelp2024.strategy.login;


public interface FacebookLoginClient {
    FacebookLoginResult verify(String token, String email);
}
