package cn.daoren.seata.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName account
 */
@TableName("account")
public class Account implements Serializable {

    /**
     *
     */
    @NotNull(message = "[]不能为空")
    private Integer id;
    /**
     *
     */

    private Double balance;
    /**
     *
     */

    private LocalDateTime lastUpdateTime;
}
