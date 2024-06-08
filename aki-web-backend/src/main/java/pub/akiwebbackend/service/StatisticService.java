package pub.akiwebbackend.service;

import pub.akiwebbackend.domain.vo.DateCountVO;
import pub.akiwebbackend.domain.vo.StabilityCountVO;

import java.util.List;

/**
 * @author cym
 * @date 2024/6/8
 */
public interface StatisticService {

    List<DateCountVO> getDailyCountsForCurrentYear();

    StabilityCountVO countByStability();

    List<List<Long>> countByRating();
}
