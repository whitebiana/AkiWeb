package pub.akiwebbackend.domain.dto.card;

import lombok.Data;

/**
 * @author cym
 * @date 2024/4/12
 */
@Data
public class CardQueryDTO {

    /**
     * 错题本名称或错题名称
     */
    private String searchCommand;
}
