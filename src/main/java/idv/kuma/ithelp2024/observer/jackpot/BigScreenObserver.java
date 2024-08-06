package idv.kuma.ithelp2024.observer.jackpot;

public class BigScreenObserver {

    BigScreenController bigScreenNotifier;

    public BigScreenObserver(BigScreenController bigScreenNotifier) {
        this.bigScreenNotifier = bigScreenNotifier;
    }

    void notifyBigScreen(JackpotHitEvent jackpotHitEvent) {
        // send prize and playerId to big screen
        bigScreenNotifier.showJackpotHit(jackpotHitEvent.jackpotHit().getPrizeCent(), jackpotHitEvent.userId());
    }
}