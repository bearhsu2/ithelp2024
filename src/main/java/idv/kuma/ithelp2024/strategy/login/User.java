package idv.kuma.ithelp2024.strategy.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private final long id;
    private final String email;

}
