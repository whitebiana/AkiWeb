package pub.akiwebbackend.domain.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import pub.akiwebbackend.domain.entiry.Card;
import pub.akiwebbackend.domain.entiry.Deck;

import java.util.Date;
import java.util.List;

/**
 * @author cym
 * @date 2024/6/9
 */
@Data
public class CardVO {
    /**
     * id
     */
    private String id;

    /**
     * deck
     */
    private Deck deck;

    /**
     * 正面
     */
    private String data;

    /**
     * 答案
     */
    private String ans;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 0=new, 1=learning, 2=review, 3=relearning
     */
    private Integer state;

    /**
     * 难度
     */
    private Double difficuty;

    /**
     * 记忆稳定性
     */
    private Double stability;

    /**
     * 复习次数
     */
    private Integer reps;

    /**
     *
     */
    private Integer lapses;

    /**
     * 过去的天数
     */
    private Double elapsedDays;

    /**
     * 复习间隔时间(天)
     */
    private Integer scheduledDays;

    /**
     * 过期时间点
     */
    private Date due;

    /**
     * 上一次复习的时间点
     */
    private Date lastReview;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 包装类转对象
     * @param cardVO 包装类对象
     * @return 原始对象
     */
    public static Card voToObj(CardVO cardVO) {
        if (cardVO == null) return null;
        Card card = new Card();
        BeanUtils.copyProperties(cardVO, card);
        List<String> tagList = cardVO.getTags();
        card.setTags(new Gson().toJson(tagList));
        return card;
    }

    /**
     * 对象转包装类
     * @param card 原始对象
     * @return 包装类对象
     */
    public static CardVO objToVo(Card card) {
        if (card == null) return null;
        CardVO cardVO = new CardVO();
        BeanUtils.copyProperties(card, cardVO);
        cardVO.setTags(new Gson().fromJson(card.getTags(), new TypeToken<List<String>>(){}.getType()));
        return cardVO;
    }
}
