package idv.kuma.ithelp2024.responsibility.chain.reference;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInvitation {
    private final long inviterId;
    private final long inviteeId;
}
