package idv.kuma.ithelp2024.observer.jackpot;

public class FakeJackpotPoolSettingCreator implements JackpotPoolSettingCreator {


    private JackpotPoolSetting next;

    public void setNext(JackpotPoolSetting next) {
        this.next = next;
    }

    @Override
    public JackpotPoolSetting getNext() {
        return next;
    }
}
