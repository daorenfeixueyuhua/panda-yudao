package cn.daoren.seata.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName product
 */
@TableName("product")
public class Product implements Serializable {

    /**
     *
     */
    @NotNull(message = "[]不能为空")

    private Integer id;
    /**
     *
     */

    private Integer stock;
    /**
     *
     */

    private LocalDateTime lastUpdateTime;
}
