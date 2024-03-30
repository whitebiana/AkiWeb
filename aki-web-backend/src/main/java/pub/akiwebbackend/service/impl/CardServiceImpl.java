package pub.akiwebbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.akiwebbackend.domain.entiry.Card;
import pub.akiwebbackend.service.CardService;
import pub.akiwebbackend.mapper.CardMapper;
import org.springframework.stereotype.Service;

/**
* @author cym
* @description 针对表【card(card)】的数据库操作Service实现
* @createDate 2024-03-30 23:20:21
*/
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card>
    implements CardService{

}




