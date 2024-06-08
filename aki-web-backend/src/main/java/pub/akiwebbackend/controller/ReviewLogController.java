package pub.akiwebbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.akiwebbackend.common.ErrorCode;
import pub.akiwebbackend.common.R;
import pub.akiwebbackend.domain.dto.review_log.ReviewLogAddDTO;
import pub.akiwebbackend.domain.entiry.ReviewLog;
import pub.akiwebbackend.exception.BusinessException;
import pub.akiwebbackend.service.ReviewLogService;

/**
 * @author cym
 * @date 2024/6/5
 */
@Tag(name = "复习日志接口")
@RestController
@RequestMapping("/review_log")
public class ReviewLogController {

    @Resource
    private ReviewLogService reviewLogService;

    @Operation(summary = "记录错题日志")
    @PostMapping("/add")
    public R addReviewLog(@RequestBody ReviewLogAddDTO reviewLogAddDTO) {
        // 构建对象
        ReviewLog reviewLog = new ReviewLog();
        BeanUtils.copyProperties(reviewLogAddDTO, reviewLog);


        boolean res = reviewLogService.save(reviewLog);
        if (!res) throw new BusinessException(ErrorCode.SERVICE_ERROR_C0300);

        return R.success(reviewLog.getId());
    }
}
