package cn.daoren.druid.dal.mysql;

import cn.daoren.druid.dal.dataobject.User;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapperX<User> {
}
