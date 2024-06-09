package pub.akiwebbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import pub.akiwebbackend.domain.entiry.Card;
import pub.akiwebbackend.domain.entiry.Deck;
import pub.akiwebbackend.domain.vo.CardVO;
import pub.akiwebbackend.mapper.CardMapper;
import pub.akiwebbackend.mapper.DeckMapper;
import pub.akiwebbackend.service.CardService;

/**
* @author cym
* @description 针对表【card(card)】的数据库操作Service实现
* @createDate 2024-03-30 23:20:21
*/
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card>
    implements CardService{

    @Resource
    private DeckMapper deckMapper;

    @Override
    public CardVO getCardVO(Card card) {
        CardVO cardVO = CardVO.objToVo(card);
        Deck deck = deckMapper.selectById(card.getDid());
        cardVO.setDeck(deck);
        return cardVO;
    }
}




