package pub.akiwebbackend.domain.entiry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * review_log
 * @TableName review_log
 */
@TableName(value ="review_log")
@Data
public class ReviewLog implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * card id
     */
    private String cid;

    /**
     * 1=Again, 2=Hard, 3=Good, 4=Easy
     */
    private Integer rating;

    /**
     * 0=new, 1=learning, 2=review, 3=relearning
     */
    private Integer state;

    /**
     * 经过的天数
     */
    private Double elapsed_days;

    /**
     * 复习间隔时间(天)
     */
    private Integer scheduled_days;

    /**
     * 上一次复习的时间点
     */
    private Date review;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}