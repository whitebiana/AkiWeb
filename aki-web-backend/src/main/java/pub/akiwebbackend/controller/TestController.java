package pub.akiwebbackend.controller;

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
