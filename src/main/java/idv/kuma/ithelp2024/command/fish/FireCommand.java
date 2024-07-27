package idv.kuma.ithelp2024.command.fish;

import lombok.Getter;

@Getter
public class FireCommand implements Command {
    private final int position;
    private final long bulletId;
    private final int direction;

    public FireCommand(int position, long bulletId, int direction) {
        this.position = position;
        this.bulletId = bulletId;
        this.direction = direction;
    }

    void executeFire(Fishpond fishpond) {
        FireCommand fireCommand = this;
        fishpond.getBullets().add(new Bullet(fireCommand.getPosition(), fireCommand.getBulletId(), fireCommand.getDirection()));
    }
}
