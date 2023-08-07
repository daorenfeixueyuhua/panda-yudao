package cn.daoren.druid.service;

import cn.daoren.druid.dal.dataobject.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    User getUser(Integer userId);

    boolean saveOrUpdateUser(User user);
}
