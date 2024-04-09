package pub.akiwebbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pub.akiwebbackend.common.ErrorCode;
import pub.akiwebbackend.common.R;
import pub.akiwebbackend.domain.dto.deck.DeckAddDTO;
import pub.akiwebbackend.domain.dto.deck.DeckEditDTO;
import pub.akiwebbackend.domain.entiry.Deck;
import pub.akiwebbackend.exception.BusinessException;
import pub.akiwebbackend.service.DeckService;

/**
 * @author cym
 * @date 2024/3/30
 */
@Tag(name = "错题本接口")
@RestController
@RequestMapping("/deck")
public class DeckController {

    @Resource
    private DeckService deckService;

    /**
     * 新建错题本
     * @param deckAddDTO 错题本创建请求
     * @return 新错题本ID
     */
    @Operation(summary = "新建错题本")
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add")
    public R addDeck(@RequestBody DeckAddDTO deckAddDTO) {
        if (deckAddDTO == null || deckAddDTO.getName() == null) {
            throw new BusinessException(ErrorCode.USER_ERROR_A0400);
        }

        // 构建对象
        Deck deck = new Deck();
        deck.setName(deckAddDTO.getName());

        QueryWrapper<Deck> wrapper = new QueryWrapper<>();
        wrapper.eq("name", deck.getName());
        if (deckService.exists(wrapper)) throw new BusinessException(ErrorCode.ERROR, "错题本名称已存在");

        // 保存对象
        boolean res = deckService.save(deck);
        if (!res) throw new BusinessException(ErrorCode.SERVICE_ERROR_C0300);

        return R.success(deck.getId());
    }

    /**
     * 删除错题本
     * @param id deck id
     * @return 删除成功或失败
     */
    @Operation(summary = "删除错题本")
    @PostMapping("/delete/{id}")
    public R deleteDeck(@PathVariable String id) {
        // 直接删
        boolean res = deckService.removeById(id);
        if (!res) throw new BusinessException(ErrorCode.USER_ERROR_A0404);

        return R.success();
    }

    /**
     * 重名名错题本
     * @param deckEditDTO 错题本编辑请求
     * @return 重命名成功或失败
     */
    @Operation(summary = "重命名错题本")
    @PostMapping("/rename")
    public R renameDeck(@RequestBody DeckEditDTO deckEditDTO) {
        if (deckEditDTO == null || deckEditDTO.getId() == null) {
            throw new BusinessException(ErrorCode.USER_ERROR_A0400);
        }

        // 构建对象
        Deck deck = new Deck();
        BeanUtils.copyProperties(deckEditDTO, deck);

        // 更新对象
        boolean res = deckService.updateById(deck);
        if (!res) throw new BusinessException(ErrorCode.SERVICE_ERROR_C0300);
        return R.success();
    }

    /**
     * 分页查询错题本列表
     * @param currentPage 当前页数
     * @param pageSize 页面大小
     * @return 分页信息
     */
    @Operation(summary = "分页查询错题本列表")
    @PostMapping("/{currentPage}/{pageSize}")
    public R getDeckList(@PathVariable int currentPage, @PathVariable int pageSize) {
        if (currentPage <= 0 || pageSize <= 0) {
            throw new BusinessException(ErrorCode.USER_ERROR_A0400);
        }

        // 直接查
        return R.success(deckService.page(new Page<>(currentPage, pageSize)));
    }
}
