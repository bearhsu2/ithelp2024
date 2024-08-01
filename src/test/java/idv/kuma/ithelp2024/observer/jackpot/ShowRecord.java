package idv.kuma.ithelp2024.observer.jackpot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ShowRecord {
    private final long prizeCent;
    private final long userId;

}
