package idv.kuma.ithelp2024.strategy.login;

import java.util.HashMap;
import java.util.Map;

public class DummyUserRepository implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User find(long id) {
        return users.get(id);
    }

    @Override
    public void add(User user) {
        this.users.put(user.getId(), user);
    }
}
