package idv.kuma.ithelp2024.command.fish;

import lombok.Data;

@Data
public class Bullet {
    private final int position;
    private final long bulletId;
    private final int direction;

    public Bullet(int position, long bulletId, int direction) {

        this.position = position;
        this.bulletId = bulletId;
        this.direction = direction;
    }
}
