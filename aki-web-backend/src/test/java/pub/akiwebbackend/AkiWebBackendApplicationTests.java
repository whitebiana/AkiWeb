package pub.akiwebbackend;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pub.akiwebbackend.domain.vo.DateCountVO;
import pub.akiwebbackend.mapper.ReviewLogMapper;

import java.util.List;

@SpringBootTest
class AkiWebBackendApplicationTests {

    @Resource
    private ReviewLogMapper reviewLogMapper;

    @Test
    void contextLoads() {
        List<DateCountVO> dailyCountsForCurrentYear = reviewLogMapper.getDailyCountsForCurrentYear();
        System.out.println(dailyCountsForCurrentYear);
    }

}
