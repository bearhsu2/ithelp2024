package idv.kuma.ithelp2024.observer.jackpot;

public record JackpotHitEvent(JackpotHit jackpotHit, long userId, long machineId) {
}