package pub.akiwebbackend.domain.entiry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    @TableId
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date gmt_create;

    /**
     * 修改时间
     */
    private Date gmt_modified;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer is_deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}