package idv.kuma.ithelp2024.responsibility.chain.reference;

import java.util.Optional;

public interface UserReferenceRepository {
    Optional<UserReference> find(long referenceeId);
}
