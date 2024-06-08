package pub.akiwebbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pub.akiwebbackend.domain.entiry.ReviewLog;
import pub.akiwebbackend.domain.vo.DateCountVO;

import java.time.LocalDate;
import java.util.List;

/**
* @author cym
* @description 针对表【review_log(review_log)】的数据库操作Mapper
* @createDate 2024-06-05 15:28:43
* @Entity pub.akiwebbackend.domain.entiry.ReviewLog
*/
@Mapper
public interface ReviewLogMapper extends BaseMapper<ReviewLog> {

    List<DateCountVO> getDailyCountsForCurrentYear();

    List<Long> countCardsByRatingAndDate(LocalDate date);
}




