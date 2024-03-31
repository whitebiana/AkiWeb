package pub.akiwebbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.akiwebbackend.common.R;
import pub.akiwebbackend.service.DeckService;

/**
 * @author cym
 * @date 2024/3/30
 */
@Tag(name = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private DeckService deckService;

    @GetMapping("/hello")
    public R hello() {
        return R.success("helloasdfasdf");
    }

    @GetMapping("/list")
    public R list() {
        return R.success(deckService.list());
    }
}
