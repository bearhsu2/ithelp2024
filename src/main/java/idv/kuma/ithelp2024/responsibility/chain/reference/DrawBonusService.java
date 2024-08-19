package idv.kuma.ithelp2024.responsibility.chain.reference;

import idv.kuma.ithelp2024.strategy.login.User;
import idv.kuma.ithelp2024.strategy.login.UserRepository;

import java.util.Optional;

public class DrawBonusService {

    private final UserRepository userRepository;
    private final BonusRepository bonusRepository;
    private final DrawBonusRecordRepository drawBonusRecordRepository;

    public DrawBonusService(UserRepository userRepository, BonusRepository bonusRepository, DrawBonusRecordRepository drawBonusRecordRepository) {
        this.userRepository = userRepository;
        this.bonusRepository = bonusRepository;
        this.drawBonusRecordRepository = drawBonusRecordRepository;
    }

    // 建立一個 Bonus 物件
    // 當用戶想要領取獎金時，呼叫此方法
    // 如果用不存在，則丟錯
    // 如果已領過，則不再領取，並丟錯
    // 如果還沒領過，則領取，並新增一筆領取紀錄
    public void draw(long userId, String bonusCode) throws Exception {


        Optional<DrawBonusRecord> drawBonusRecordOpt = drawBonusRecordRepository.find(userId, bonusCode);
        if (drawBonusRecordOpt.isPresent()) {
            throw new Exception("Already drawn");
        }

        Bonus bonus = bonusRepository.findByCode(bonusCode)
                .orElseThrow(() -> new Exception("Bonus not found"));

        User user = Optional.ofNullable(userRepository.find(userId))
                .orElseThrow(() -> new Exception("User not found"));

        user.getWallet().add(bonus.bonusAmount());

        DrawBonusRecord drawBonusRecord = new DrawBonusRecord(userId, bonusCode);
        drawBonusRecordRepository.save(drawBonusRecord);

        userRepository.save(user);


    }
}
