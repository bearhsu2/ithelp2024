package idv.kuma.ithelp2024.observer.jackpot;

import java.util.ArrayList;
import java.util.List;

public class FakeBigScreenController implements BigScreenController {

    List<ShowRecord>  showRecords = new ArrayList<>();

    @Override
    public void showJackpotHit(long prizeCent, long userId) {
        this.showRecords.add(new ShowRecord(prizeCent, userId));
    }
}
