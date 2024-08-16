package idv.kuma.ithelp2024.responsibility.chain.reference;

import java.util.Optional;

public interface UserInvitationRepository {
    Optional<UserInvitation> find(long inviteeId);

    void save(UserInvitation userInvitation);
}
