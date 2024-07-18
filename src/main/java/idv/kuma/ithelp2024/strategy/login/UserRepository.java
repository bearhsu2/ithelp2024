package idv.kuma.ithelp2024.strategy.login;

public interface UserRepository {

    User find(long id);

    void add(User user);
}
