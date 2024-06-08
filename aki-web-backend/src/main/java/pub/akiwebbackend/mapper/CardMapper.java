package pub.akiwebbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pub.akiwebbackend.domain.entiry.Card;

import java.time.LocalDate;

/**
* @author cym
* @description 针对表【card(card)】的数据库操作Mapper
* @createDate 2024-03-30 23:20:21
* @Entity pub.akiwebbackend.domain.entiry.Card
*/
@Mapper
public interface CardMapper extends BaseMapper<Card> {

    @Select("select count(*) from card where is_deleted = 0 and last_review < date_add(#{date}, interval 1 day) and stability >= #{rating}")
    Long countCardsByStabilityAndDate(LocalDate date, Double rating);

    @Select("select count(*) from card where is_deleted = 0 and gmt_create < date_add(#{date}, interval 1 day)")
    Long countCardsByDate(LocalDate date);
}




