package pub.akiwebbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.akiwebbackend.common.R;
import pub.akiwebbackend.service.StatisticService;

/**
 * @author cym
 * @date 2024/6/8
 */
@Tag(name = "统计接口")
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Resource
    private StatisticService statisticService;

    @Operation(summary = "获取日历热力图数据")
    @GetMapping("/daily")
    public R getDailyCountsForCurrentYear() {
        return R.success(statisticService.getDailyCountsForCurrentYear());
    }
}
