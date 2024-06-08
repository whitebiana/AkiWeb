package pub.akiwebbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.akiwebbackend.domain.entiry.ReviewLog;
import pub.akiwebbackend.service.ReviewLogService;
import pub.akiwebbackend.mapper.ReviewLogMapper;
import org.springframework.stereotype.Service;

/**
* @author cym
* @description 针对表【review_log(review_log)】的数据库操作Service实现
* @createDate 2024-06-05 15:28:43
*/
@Service
public class ReviewLogServiceImpl extends ServiceImpl<ReviewLogMapper, ReviewLog>
    implements ReviewLogService{

}




