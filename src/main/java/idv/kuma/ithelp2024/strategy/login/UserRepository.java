package idv.kuma.ithelp2024.strategy.login;

public interface UserRepository {

    void save(User user);

    User find(long userId);
}
