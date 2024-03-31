package pub.akiwebbackend.domain.entiry;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * card
 * @TableName card
 */
@TableName(value ="card")
@Data
public class Card implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * deck id
     */
    private Long did;

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
    private String tags;

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
    @TableLogic
    private Integer isDeleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}