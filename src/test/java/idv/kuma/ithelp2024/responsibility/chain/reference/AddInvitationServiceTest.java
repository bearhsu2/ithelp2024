package idv.kuma.ithelp2024.responsibility.chain.reference;

import idv.kuma.ithelp2024.strategy.login.DummyUserRepository;
import idv.kuma.ithelp2024.strategy.login.User;
import idv.kuma.ithelp2024.strategy.login.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class AddInvitationServiceTest {

    private UserRepository userRepository = new DummyUserRepository();
    private UserInvitationRepository userInvitationRepository = new FakeUserInvitationRepository();
    private AddInvitationService sut = new AddInvitationService(userRepository, userInvitationRepository);

    @Test
    void invitee_not_found() throws Exception {

        User inviter = user(1L, "john@gmail.com", "ABCD001");
        userRepository.save(inviter);

        Assertions.assertThatThrownBy(() -> sut.add(2L, "ABCD001"))
                .isInstanceOf(Exception.class)
                .hasMessage("Invitee not found");

    }

    private User user(long id, String email, String invitationCode) {
        return new User(id, email, invitationCode);
    }

    @Test
    void inviter_not_found() throws Exception {

        User invitee = user(2L, "mary@gmail.com", "XYZZ996");
        userRepository.save(invitee);

        Assertions.assertThatThrownBy(() -> sut.add(2L, "ABCD001"))
                .isInstanceOf(Exception.class)
                .hasMessage("Inviter not found");

    }

    @Test
    void all_ok() throws Exception {

        User inviter = user(1L, "john@gmail.com", "ABCD001");
        User invitee = user(2L, "mary@gmail.com", "XYZZ996");

        userRepository.save(inviter);
        userRepository.save(invitee);

        sut.add(2L, "ABCD001");

        Assertions.assertThat(userInvitationRepository.find(invitee.getId()))
                .contains(new UserInvitation(1L, 2L));

    }

    @Test
    void already_invited() throws Exception {

        User inviter = user(1L, "john@gmail.com", "ABCD001");
        User invitee = user(2L, "mary@gmail.com", "XYZZ996");
        User third_guy = user(2L, "mary@gmail.com", "STHELSE");

        userRepository.save(inviter);
        userRepository.save(invitee);
        userRepository.save(third_guy);

        sut.add(2L, "ABCD001");

        Assertions.assertThatThrownBy(() -> sut.add(2L, "STHELSE"))
                .isInstanceOf(Exception.class)
                .hasMessage("Already invited");
        
    }
}