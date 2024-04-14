package pub.akiwebbackend.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;
import pub.akiwebbackend.common.ErrorCode;
import pub.akiwebbackend.common.R;
import pub.akiwebbackend.domain.dto.card.CardAddDTO;
import pub.akiwebbackend.domain.dto.card.CardEditDTO;
import pub.akiwebbackend.domain.dto.card.CardQueryDTO;
import pub.akiwebbackend.domain.dto.card.CardUpdateDTO;
import pub.akiwebbackend.domain.entiry.Card;
import pub.akiwebbackend.domain.entiry.Deck;
import pub.akiwebbackend.exception.BusinessException;
import pub.akiwebbackend.service.CardService;
import pub.akiwebbackend.service.DeckService;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/{id}")
    @Operation(summary = "查询一条错题")
    public R getCard(@PathVariable String id){
        return R.success(cardService.getById(id));
    }

    /**
     * 获取错题列表
     * @param cardQueryDTO 搜索参数
     * @return 错题列表
     */
    @PostMapping("/list")
    @Operation(summary = "搜索错题")
    public R getCardList(@RequestBody CardQueryDTO cardQueryDTO) {
        if (cardQueryDTO == null || cardQueryDTO.getSearchCommand() == null) {
            throw new BusinessException(ErrorCode.USER_ERROR_A0400);
        }

        String searchCommand = cardQueryDTO.getSearchCommand();

        // 搜索逻辑 先将命令拆分成数组 然后使用拼接条件查询
        // 限制最多查询100条
        String LIMIT = "limit 100";

        // 1.输入空的值就搜索所有的卡片：限制显示100条
         if (StringUtils.isBlank(searchCommand)) {
             return R.success(cardService.list(new LambdaQueryWrapper<Card>().last(LIMIT)));
         }

        // 2.前缀一定是"deck:"，如果是空值或者是其他的乱输入就返回空
        if (searchCommand.startsWith("deck:")) {
            String deckName = searchCommand.substring(5);

            if (StringUtils.isBlank(deckName)) { // deck:
                return R.success(new ArrayList<>());
            } else { // deck:xxxxx
                // 创建查询条件
                QueryWrapper<Deck> deckQueryWrapper = new QueryWrapper<>();
                // *注：错题本是唯一的不能使用模糊查询
                deckQueryWrapper.eq("name",deckName);
                Deck deck = deckService.getOne(deckQueryWrapper);

                if ( deck == null ){
                    return R.success(new ArrayList<>());
                }

                // 3.返回该错题本的所有题目
                // 获取到deck的id
                String deckId = deck.getId();
                // 创建查询条件
                QueryWrapper<Card> cardQueryWrapper = new QueryWrapper<>();
                cardQueryWrapper.eq("did",deckId).last(LIMIT);
                // 查询该错题本的所有题目，超过一百条要冲会员
                List<Card> cardList = cardService.list(cardQueryWrapper);
                return R.success(cardList);
            }
        } else { // 输入的是题目的名称直接查询得到并返回使用queryWrapper拼接查询
            //创建查询条件
            QueryWrapper<Card> wrapper = new QueryWrapper<>();
            wrapper.like("data",searchCommand).last(LIMIT);
            List<Card> card = cardService.list(wrapper);
            return R.success(card);
        }
    }

    /**
     * 删除单个错题
     * @param id 传入要删除的错题id
     * @return
     */
    @Operation(summary = "删除错题")
    @PostMapping("/delete/{id}")
    public R deleteCard(@PathVariable String id){
        //直接删除
        boolean isRemove = cardService.removeById(id);
        if (!isRemove) throw new BusinessException(ErrorCode.USER_ERROR_A0404);
        return R.success();
    }

    /**
     * 复习的时候更新错题
     * @param cardUpdateDTO
     * @return
     */
    @Operation(summary = "更新复习的错题数据")
    @PostMapping("/update")
    public R updateCard(@RequestBody CardUpdateDTO cardUpdateDTO){
        Card card = new Card();
        BeanUtils.copyProperties(cardUpdateDTO,card);
        boolean isUpdate = cardService.updateById(card);
        if (!isUpdate) throw new BusinessException(ErrorCode.SERVICE_ERROR_C0300);
        return R.success("数据跟新成功");
    }

    /**
     * 更新错题
     * @param cardEditDTO
     * @return
     */
    @Operation(summary = "编辑错题数据")
    @PostMapping("/edit")
    public R editCard(@RequestBody CardEditDTO cardEditDTO){
        Card card = new Card();
        BeanUtils.copyProperties(cardEditDTO,card);
        card.setTags(new Gson().toJson(cardEditDTO.getTags()));
        boolean isUpdate = cardService.updateById(card);
        if (!isUpdate) throw new BusinessException(ErrorCode.SERVICE_ERROR_C0300);
        return R.success();
    }


}
