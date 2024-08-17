package idv.kuma.ithelp2024.responsibility.chain.reference;

import idv.kuma.ithelp2024.strategy.login.User;
import idv.kuma.ithelp2024.strategy.login.UserRepository;

import java.util.Optional;

public class AcceptInvitationService {
    private final UserRepository userRepository;
    private final UserInvitationRepository userInvitationRepository;

    public AcceptInvitationService(UserRepository userRepository, UserInvitationRepository userInvitationRepository) {
        this.userRepository = userRepository;
        this.userInvitationRepository = userInvitationRepository;
    }

    public void add(long inviteeId, String code) throws Exception {

        User inviter = userRepository.findByCode(code)
                .orElseThrow(() -> new Exception("Inviter not found"));
        User invitee = Optional.ofNullable(userRepository.find(inviteeId))
                .orElseThrow(() -> new Exception("Invitee not found"));

        Optional<UserInvitation> userInvitationOpt = userInvitationRepository.find(inviteeId);
        if (userInvitationOpt.isPresent()) {
            throw new Exception("Already invited");
        }

        UserInvitation userInvitation = new UserInvitation(
                inviter.getId(), inviteeId
        );
        inviter.getWallet().add(1_000);
        invitee.getWallet().add(1_000);

        userInvitationRepository.save(userInvitation);
        userRepository.save(inviter);
        userRepository.save(invitee);
    }
}
