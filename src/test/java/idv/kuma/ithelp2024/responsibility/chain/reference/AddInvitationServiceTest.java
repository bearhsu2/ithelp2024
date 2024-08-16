package idv.kuma.ithelp2024.responsibility.chain.reference;

import idv.kuma.ithelp2024.strategy.login.DummyUserRepository;
import idv.kuma.ithelp2024.strategy.login.User;
import idv.kuma.ithelp2024.strategy.login.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@Disabled
class AddInvitationServiceTest {
    @Test
    void all_ok() {

        UserRepository userRepository = new DummyUserRepository();
        User inviter = new User(1L, "john@gmail.com", "ABCD001");
        User invitee = new User(2L, "mary@gmail.com", "XYZZ996");

        userRepository.save(inviter);
        userRepository.save(invitee);

        UserInvitationRepository userInvitationRepository = new FakeUserInvitationRepository();
        AddInvitationService sut = new AddInvitationService(userRepository, userInvitationRepository);

        sut.add(2L, "ABCD001");


        Optional<UserInvitation> userInvitation = userInvitationRepository.find(invitee.getId());
        Assertions.assertThat(userInvitation).isNotEmpty();

    }
}