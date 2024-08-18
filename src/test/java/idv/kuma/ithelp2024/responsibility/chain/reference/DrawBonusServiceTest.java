package idv.kuma.ithelp2024.responsibility.chain.reference;

import idv.kuma.ithelp2024.strategy.login.DummyUserRepository;
import idv.kuma.ithelp2024.strategy.login.User;
import idv.kuma.ithelp2024.strategy.login.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DrawBonusServiceTest {
    @Test
    void all_ok() {

        BonusRepository bonusRepository = new FakeBonusRepository();
        bonusRepository.save(new Bonus("AAABB", 1_000));

        UserRepository userRepository = new DummyUserRepository();
        User user = User.create(1L, "abc@gmail.com", "AAABB");
        userRepository.save(user);

        DrawBonusService sut = new DrawBonusService(
                userRepository, bonusRepository
        );
        sut.draw(1L, "AAABB");

        Assertions.assertThat(userRepository.find(1L).getWallet().getBalance())
                .isEqualTo(1_000);

    }
}