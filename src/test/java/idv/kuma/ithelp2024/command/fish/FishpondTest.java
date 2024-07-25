package idv.kuma.ithelp2024.command.fish;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FishpondTest {

    @Test
    void hit() {

        Fishpond sut = new Fishpond();

        Player player1 = new Player(1L);
        Player player2 = new Player(2L);

        sut.addPlayer(player1);
        sut.addPlayer(player2);

        sut.addFish(new Fish(1L));
        sut.addFish(new Fish(2L));
        sut.addFish(new Fish(3L));
        sut.addFish(new Fish(4L));
        sut.addFish(new Fish(5L));

        sut.fire(1, 1L, 75);
        sut.hit(1, 5L);

        Assertions.assertThat(sut.getFishes()).hasSize(4);
        Assertions.assertThat(sut.getBullets()).isEmpty();

    }

    @Test
    void fire() {

        Fishpond sut = new Fishpond();

        Player player1 = new Player(1L);
        Player player2 = new Player(2L);

        sut.addPlayer(player1);
        sut.addPlayer(player2);

        sut.fire(1, 1L, 75);

        Assertions.assertThat(sut.getBullets().get(0))
                .isEqualTo(new Bullet(1, 1L, 75));

    }
}