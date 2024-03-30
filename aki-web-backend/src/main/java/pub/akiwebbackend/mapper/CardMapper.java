package pub.akiwebbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import pub.akiwebbackend.domain.entiry.Card;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author cym
* @description 针对表【card(card)】的数据库操作Mapper
* @createDate 2024-03-30 23:20:21
* @Entity pub.akiwebbackend.domain.entiry.Card
*/
@Mapper
public interface CardMapper extends BaseMapper<Card> {

}




