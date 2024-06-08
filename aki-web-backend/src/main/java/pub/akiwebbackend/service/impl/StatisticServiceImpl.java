package pub.akiwebbackend.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import pub.akiwebbackend.domain.vo.DateCountVO;
import pub.akiwebbackend.mapper.ReviewLogMapper;
import pub.akiwebbackend.service.StatisticService;

import java.util.List;

/**
 * @author cym
 * @date 2024/6/8
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Resource
    private ReviewLogMapper reviewLogMapper;

    @Override
    public List<DateCountVO> getDailyCountsForCurrentYear() {
        return reviewLogMapper.getDailyCountsForCurrentYear();
    }
}
