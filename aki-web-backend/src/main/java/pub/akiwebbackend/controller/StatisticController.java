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

    @Operation(summary = "获取错题记忆持久度数据")
    @GetMapping("/stability")
    public R countByStability() {
        return R.success(statisticService.countByStability());
    }

    @Operation(summary = "获取错题复习数据")
    @GetMapping("/rating")
    public R countByRating() {
        return R.success(statisticService.countByRating());
    }
}
