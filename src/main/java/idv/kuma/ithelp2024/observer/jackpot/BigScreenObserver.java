package idv.kuma.ithelp2024.observer.jackpot;

public class BigScreenObserver implements JackpotHitObserver {

    BigScreenController bigScreenNotifier;

    public BigScreenObserver(BigScreenController bigScreenNotifier) {
        this.bigScreenNotifier = bigScreenNotifier;
    }

    @Override
    public void notify(JackpotHitEvent jackpotHitEvent) {
        // send prize and playerId to big screen
        bigScreenNotifier.showJackpotHit(jackpotHitEvent.jackpotHit().getPrizeCent(), jackpotHitEvent.userId());
    }
}