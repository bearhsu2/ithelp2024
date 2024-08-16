package idv.kuma.ithelp2024.strategy.login;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    User find(long userId);

    Optional<User> findByCode(String code);
}
