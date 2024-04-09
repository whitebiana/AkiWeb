package pub.akiwebbackend.domain.dto.card;

import lombok.Data;

import java.util.List;

/**
 * @author cym
 * @date 2024/4/9
 */
@Data
public class CardAddDTO {
    /**
     * 错题本名称
     */
    private String deckname;

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
}
