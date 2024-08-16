package idv.kuma.ithelp2024.strategy.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DummyUserRepository implements UserRepository {

    Map<Long, User> users = new HashMap<>();


    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User find(long userId) {
        return users.get(userId);
    }

    @Override
    public Optional<User> findByCode(String invitationCode) {
        return users
                .values()
                .stream()
                .filter(user -> user.getInvitationCode().equals(invitationCode))
                .findFirst();
    }
}
