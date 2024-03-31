package pub.akiwebbackend.domain.entiry;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * deck
 * @TableName deck
 */
@TableName(value ="deck")
@Data
public class Deck implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 名称
     */
    private String name;

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