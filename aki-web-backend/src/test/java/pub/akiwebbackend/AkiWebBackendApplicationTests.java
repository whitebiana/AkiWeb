package pub.akiwebbackend;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pub.akiwebbackend.mapper.CardMapper;
import pub.akiwebbackend.mapper.ReviewLogMapper;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class AkiWebBackendApplicationTests {

    @Resource
    private ReviewLogMapper reviewLogMapper;

    @Resource
    private CardMapper cardMapper;

    @Test
    void contextLoads() {
        List<Long> list = reviewLogMapper.countCardsByRatingAndDate(LocalDate.now().minusDays(3));
        System.out.println(list);
    }

}
