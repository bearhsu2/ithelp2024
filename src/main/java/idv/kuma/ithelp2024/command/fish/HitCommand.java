package idv.kuma.ithelp2024.command.fish;

import lombok.Getter;

@Getter
public class HitCommand implements Command {
    private final long bulletId;
    private final long fishId;

    public HitCommand(long bulletId, long fishId) {

        this.bulletId = bulletId;
        this.fishId = fishId;
    }

    void executeHit(Fishpond fishpond) {
        HitCommand hitCommand = this;
        fishpond.getBullets().removeIf(bullet -> bullet.getBulletId() == hitCommand.getBulletId());
        fishpond.getFishes().removeIf(fish -> fish.getFishId() == hitCommand.getFishId());
    }
}
