package pub.akiwebbackend.domain.vo;

import lombok.Data;

/**
 * @author cym
 * @date 2024/4/24
 */
@Data
public class DeckVO {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 未学习错题数量
     */
    private Long newNum;

    /**
     * 待复习错题数量
     */
    private Long reviewNum;

    /**
     * 学习中错题数量（包括relearning）
     */
    private Long learningNum;
}
