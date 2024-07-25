package idv.kuma.ithelp2024.command.fish;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Fishpond {
    private List<Player> players = new ArrayList<>();
    @Getter
    private List<Fish> fishes = new ArrayList<>();

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addFish(Fish fish) {
        this.fishes.add(fish);
    }

    public void fire(int position, int direction) {
        for (Player player : players) {
            player.sendMessage("FIRE - position: " + position + ", direction: " + direction);
        }
    }

    public void hit(int position, long fishId) {
        this.fishes.removeIf(fish -> fish.getFishId() == fishId);
        for (Player player : players) {
            player.sendMessage("HOT - position: " + position + ", fish: " + fishId);
        }
    }

}
