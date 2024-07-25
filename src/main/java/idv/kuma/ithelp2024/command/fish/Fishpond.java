package idv.kuma.ithelp2024.command.fish;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Fishpond {

    private List<Player> players = new ArrayList<>();

    @Getter
    private List<Fish> fishes = new ArrayList<>();

    @Getter
    private List<Bullet> bullets = new ArrayList<>();

    private Queue<Command> commands = new LinkedList<>();

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addFish(Fish fish) {
        this.fishes.add(fish);
    }

    public void fire(int position, long bulletId, int direction) {

        FireCommand fireCommand = new FireCommand(position, bulletId, direction);
        commands.offer(fireCommand);

        FireCommand polled = (FireCommand) commands.poll();
        this.bullets.add(new Bullet(polled.getPosition(), polled.getBulletId(), polled.getDirection()));


    }

    public void hit(long bulletId, long fishId) {

        HitCommand hitCommand = new HitCommand(bulletId, fishId);
        this.commands.offer(hitCommand);

        HitCommand polled = (HitCommand) commands.poll();

        this.bullets.removeIf(bullet -> bullet.getBulletId() == polled.getBulletId());
        this.fishes.removeIf(fish -> fish.getFishId() == polled.getFishId());

    }

}
