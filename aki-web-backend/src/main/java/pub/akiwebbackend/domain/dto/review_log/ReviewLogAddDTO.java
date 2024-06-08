package pub.akiwebbackend.domain.dto.review_log;

import lombok.Data;

import java.util.Date;

/**
 * @author cym
 * @date 2024/6/5
 */
@Data
public class ReviewLogAddDTO {
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
}
