package pub.akiwebbackend.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import pub.akiwebbackend.domain.vo.DateCountVO;
import pub.akiwebbackend.domain.vo.StabilityCountVO;
import pub.akiwebbackend.mapper.CardMapper;
import pub.akiwebbackend.mapper.ReviewLogMapper;
import pub.akiwebbackend.service.StatisticService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cym
 * @date 2024/6/8
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Resource
    private ReviewLogMapper reviewLogMapper;

    @Resource
    private CardMapper cardMapper;

    @Override
    public List<DateCountVO> getDailyCountsForCurrentYear() {
        return reviewLogMapper.getDailyCountsForCurrentYear();
    }

    @Override
    public StabilityCountVO countByStability() {

        StabilityCountVO stabilityCountVO = new StabilityCountVO();
        List<Long> total = new ArrayList<>();
        List<Long> ten = new ArrayList<>();
        List<Long> thirty = new ArrayList<>();
        List<Long> sisty = new ArrayList<>();
        List<Long> ninety = new ArrayList<>();

        LocalDate today = LocalDate.now();
        for (int i = 7; i >= 0; i--) {
            total.add(cardMapper.countCardsByDate(today.minusDays(i)));
            ten.add(cardMapper.countCardsByStabilityAndDate(today.minusDays(i), 10.0));
            thirty.add(cardMapper.countCardsByStabilityAndDate(today.minusDays(i), 30.0));
            sisty.add(cardMapper.countCardsByStabilityAndDate(today.minusDays(i), 60.0));
            ninety.add(cardMapper.countCardsByStabilityAndDate(today.minusDays(i), 90.0));
        }
        stabilityCountVO.setTotal(total);
        stabilityCountVO.setTen(ten);
        stabilityCountVO.setThirty(thirty);
        stabilityCountVO.setSisty(sisty);
        stabilityCountVO.setNinety(ninety);

        return stabilityCountVO;
    }

    @Override
    public List<List<Long>> countByRating() {
        List<List<Long>> res = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 7; i >= 0; i--) {
            res.add(reviewLogMapper.countCardsByRatingAndDate(today.minusDays(i)));
        }
        return res;
    }
}
