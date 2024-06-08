package pub.akiwebbackend.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author cym
 * @date 2024/6/8
 */
@Data
public class StabilityCountVO {
    List<Long> total;
    List<Long> ten;
    List<Long> thirty;
    List<Long> sisty;
    List<Long> ninety;
}
