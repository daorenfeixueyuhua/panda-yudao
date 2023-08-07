package cn.daoren.druid.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author peng_da
 * @since 2023/5/12 9:28
 */
@Data
@TableName("sys_user")
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
}
