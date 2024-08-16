package idv.kuma.ithelp2024.responsibility.chain.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUserInvitationRepository implements UserInvitationRepository {

    Map<Long, UserInvitation> inviteeToInvitations = new HashMap<>();

    @Override
    public Optional<UserInvitation> find(long inviteeId) {
        return Optional.ofNullable(inviteeToInvitations.get(inviteeId));
    }

    @Override
    public void save(UserInvitation userInvitation) {
        inviteeToInvitations.put(userInvitation.getInviteeId(), userInvitation);
    }


}
