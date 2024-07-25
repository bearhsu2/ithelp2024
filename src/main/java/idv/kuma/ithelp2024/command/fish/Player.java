package idv.kuma.ithelp2024.command.fish;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final long playerId;
    private List<String> messages = new ArrayList<>();

    public Player(long playerId) {
        this.playerId = playerId;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void sendMessage(String message) {

        this.messages.add(message);

    }
}
