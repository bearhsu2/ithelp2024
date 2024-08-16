package idv.kuma.ithelp2024.responsibility.chain.reference;

import idv.kuma.ithelp2024.strategy.login.User;
import idv.kuma.ithelp2024.strategy.login.UserRepository;

import java.util.Optional;

public class AddInvitationService {
    private final UserRepository userRepository;
    private final UserInvitationRepository userInvitationRepository;

    public AddInvitationService(UserRepository userRepository, UserInvitationRepository userInvitationRepository) {
        this.userRepository = userRepository;
        this.userInvitationRepository = userInvitationRepository;
    }

    public void add(long inviteeId, String code) throws Exception {

        User inviter = userRepository.findByCode(code).orElseThrow(() -> new Exception("Inviter not found"));
        User invitee = Optional.ofNullable(userRepository.find(inviteeId)).orElseThrow(() -> new Exception("Invitee not found"));

        Optional<UserInvitation> userInvitationOpt = userInvitationRepository.find(inviteeId);
        if (userInvitationOpt.isPresent()) {
            throw new Exception("Already invited");
        }

        UserInvitation userInvitation = new UserInvitation(
                inviter.getId(), inviteeId
        );

        userInvitationRepository.save(userInvitation);


    }

    //1. 每個 User 都有一個專屬邀請碼
    //2. 新玩家輸入別人的邀請碼時，先透過邀請碼找到老玩家
    //3. 確認新玩家還沒被別人邀請過
    //4. 記錄這次的邀請關係，以免多次贈獎
    //5. 為這兩位玩家都加 1,000 遊戲點數
}
