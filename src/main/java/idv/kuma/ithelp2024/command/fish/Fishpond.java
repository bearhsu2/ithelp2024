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

    public void fire(FireCommand fireCommand) {

        commands.offer(fireCommand);


    }

    public void hit(HitCommand hitCommand) {

        this.commands.offer(hitCommand);

    }

    public void executeBatch() {

        while (!commands.isEmpty()) {
            Command polled = commands.poll();

            if (polled instanceof FireCommand) {
                FireCommand fireCommand = (FireCommand) polled;
                this.bullets.add(new Bullet(fireCommand.getPosition(), fireCommand.getBulletId(), fireCommand.getDirection()));
            } else if (polled instanceof HitCommand) {
                HitCommand hitCommand = (HitCommand) polled;
                this.bullets.removeIf(bullet -> bullet.getBulletId() == hitCommand.getBulletId());
                this.fishes.removeIf(fish -> fish.getFishId() == hitCommand.getFishId());
            }
        }

    }
}
