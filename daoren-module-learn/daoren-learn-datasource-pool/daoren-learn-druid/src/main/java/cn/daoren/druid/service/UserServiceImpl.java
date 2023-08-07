package cn.daoren.druid.service;

import cn.daoren.druid.dal.dataobject.User;
import cn.daoren.druid.dal.mysql.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        log.trace("TRACE 日志");
        log.info("INFO 日志");
        log.debug("DEBUG 日志");
        log.error("ERROR 日志");

        return userMapper.selectList();
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateUser(User user) {
        final User userTemp = userMapper.selectById(user.getId());
        if (userTemp != null) {
            userMapper.updateById(user);
        } else {
            userMapper.insert(user);
        }
        return true;
    }

    private void javaBasic() {
        Integer v1 = 1;
        Integer v2 = Integer.valueOf(2);
        Short s1 = 1;
        Character c1 = 'c';
        Byte b1 = new Byte("00");

        String st1 = "123123";
    }
}
