package pub.akiwebbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.akiwebbackend.common.ErrorCode;
import pub.akiwebbackend.common.R;
import pub.akiwebbackend.domain.dto.card.CardAddDTO;
import pub.akiwebbackend.domain.entiry.Card;
import pub.akiwebbackend.domain.entiry.Deck;
import pub.akiwebbackend.exception.BusinessException;
import pub.akiwebbackend.service.CardService;
import pub.akiwebbackend.service.DeckService;

/**
 * @author cym
 * @date 2024/3/30
 */
@Tag(name = "错题接口")
@RestController
@RequestMapping("/card")
public class CardController {

    @Resource
    private CardService cardService;

    @Resource
    private DeckService deckService;

    /**
     * 添加错题
     * @param cardAddDTO 添加错题请求
     * @return 错题ID
     */
    @Operation(summary = "添加错题")
    @PostMapping("/add")
    public R addCard(@RequestBody CardAddDTO cardAddDTO) {
        if (cardAddDTO == null || StringUtils.isAnyBlank(cardAddDTO.getDeckname(), cardAddDTO.getData(), cardAddDTO.getAns())) {
            throw new BusinessException(ErrorCode.USER_ERROR_A0400);
        }

        // 如果错题本不存在
        QueryWrapper<Deck> wrapper = new QueryWrapper<>();
        wrapper.eq("name", cardAddDTO.getDeckname());

        Deck deck = deckService.getOne(wrapper);
        if (deck == null) throw new BusinessException(ErrorCode.ERROR, "错题本不存在");

        // 构建对象
        Card card = new Card();
        BeanUtils.copyProperties(cardAddDTO, card);
        card.setTags(new Gson().toJson(cardAddDTO.getTags()));
        card.setDid(deck.getId());

        boolean res = cardService.save(card);
        if (!res) throw new BusinessException(ErrorCode.SERVICE_ERROR_C0300);

        return R.success(card.getId());
    }
}
