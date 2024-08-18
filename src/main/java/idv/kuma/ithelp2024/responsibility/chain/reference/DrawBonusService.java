package idv.kuma.ithelp2024.responsibility.chain.reference;

import idv.kuma.ithelp2024.strategy.login.User;
import idv.kuma.ithelp2024.strategy.login.UserRepository;

import java.util.Optional;

public class DrawBonusService {

    private final UserRepository userRepository;
    private final BonusRepository bonusRepository;

    public DrawBonusService(UserRepository userRepository, BonusRepository bonusRepository) {
        this.userRepository = userRepository;
        this.bonusRepository = bonusRepository;
    }

    // 建立一個 Bonus 物件
    // 當用戶想要領取獎金時，呼叫此方法
    // 如果用不存在，則丟錯
    // 如果已領過，則不再領取，並丟錯
    // 如果還沒領過，則領取，並新增一筆領取紀錄
    public void draw(long userId, String bonusCode) {

        Optional<Bonus> bonusOpt = bonusRepository.findByCode(bonusCode);
        Bonus bonus = bonusOpt.get();

        User user = userRepository.find(userId);

        user.getWallet().add(bonus.bonusAmount());

        userRepository.save(user);


    }
}
