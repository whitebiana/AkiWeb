package pub.akiwebbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pub.akiwebbackend.domain.entiry.Card;
import pub.akiwebbackend.domain.vo.CardVO;

/**
* @author cym
* @description 针对表【card(card)】的数据库操作Service
* @createDate 2024-03-30 23:20:21
*/
public interface CardService extends IService<Card> {

    CardVO getCardVO(Card card);

}
