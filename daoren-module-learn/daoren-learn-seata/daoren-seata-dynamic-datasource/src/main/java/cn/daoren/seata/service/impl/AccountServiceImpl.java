package cn.daoren.seata.service.impl;

import cn.daoren.seata.domain.entity.Account;
import cn.daoren.seata.mapper.AccountMapper;
import cn.daoren.seata.service.AccountService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author pengda
 * @description 针对表【account】的数据库操作Service实现
 * @createDate 2023-08-03 16:34:07
 */
@DS("amount-ds")
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
        implements AccountService {

}




