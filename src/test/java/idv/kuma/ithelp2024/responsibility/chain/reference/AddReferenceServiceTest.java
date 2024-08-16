package idv.kuma.ithelp2024.responsibility.chain.reference;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@Disabled
class AddReferenceServiceTest {
    @Test
    void all_ok() {

        User referencer = new User(1L, "ABCD001");
        User referencee = new User(2L, "XYZZ996");

        AddReferenceService sut = new AddReferenceService();

        sut.add(2L, "ABCD001");

        UserReferenceRepository userRererenceRepository = null;

        Optional<UserReference> userReference = userRererenceRepository.find(referencee.getId());
        Assertions.assertThat(userReference).isNotEmpty();

    }
}