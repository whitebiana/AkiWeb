package pub.akiwebbackend.domain.dto.card;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serial;
import java.util.Date;
import java.util.List;

/**
 * @author cym
 * @date 2024/4/9
 */
@Data
public class CardEditDTO {
    /**
     * id
     */
    private String id;

    /**
     * did 错题本的id
     */
    private String did;

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
