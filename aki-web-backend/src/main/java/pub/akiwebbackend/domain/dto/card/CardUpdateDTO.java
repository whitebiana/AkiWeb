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
public class CardUpdateDTO {
    /**
     * id
     */
    private String id;

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

}
