package idv.kuma.ithelp2024.command.fish;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Fishpond {
    private List<Player> players = new ArrayList<>();
    @Getter
    private List<Fish> fishes = new ArrayList<>();

    @Getter
    private List<Bullet> bullets = new ArrayList<>();

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addFish(Fish fish) {
        this.fishes.add(fish);
    }

    public void fire(int position, long bulletId, int direction) {
        this.bullets.add(new Bullet(position, bulletId, direction));
    }

    public void hit(long bulletId, long fishId) {
        this.bullets.removeIf(bullet -> bullet.getBulletId() == bulletId);
        this.fishes.removeIf(fish -> fish.getFishId() == fishId);
    }

}
