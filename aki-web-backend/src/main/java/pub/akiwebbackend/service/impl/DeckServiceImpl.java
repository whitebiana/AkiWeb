package pub.akiwebbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.akiwebbackend.domain.entiry.Deck;
import pub.akiwebbackend.service.DeckService;
import pub.akiwebbackend.mapper.DeckMapper;
import org.springframework.stereotype.Service;

/**
* @author cym
* @description 针对表【deck(deck)】的数据库操作Service实现
* @createDate 2024-03-30 23:20:21
*/
@Service
public class DeckServiceImpl extends ServiceImpl<DeckMapper, Deck>
    implements DeckService{

}




