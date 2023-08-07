package cn.daoren.seata.domain.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @TableName orders
 */
@Data
public class Orders implements Serializable {

    /**
     *
     */
    @NotNull(message = "[]不能为空")

    private Integer id;
    /**
     *
     */

    private Integer userId;
    /**
     *
     */

    private Integer productId;
    /**
     *
     */

    private Integer payAmount;
    /**
     *
     */

    private Date addTime;
    /**
     *
     */

    private LocalDateTime lastUpdateTime;
}
