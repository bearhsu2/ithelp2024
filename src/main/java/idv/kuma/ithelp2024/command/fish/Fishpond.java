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

    public void addCommand(Command command) {
        commands.offer(command);
    }

    public void executeBatch() {

        while (!commands.isEmpty()) {
            Command polled = commands.poll();

            polled.execute(this);

        }

    }

}
