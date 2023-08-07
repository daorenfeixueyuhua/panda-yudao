package cn.daoren.seata.service.impl;

import cn.daoren.seata.domain.entity.Orders;
import cn.daoren.seata.mapper.OrdersMapper;
import cn.daoren.seata.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author pengda
 * @description 针对表【orders】的数据库操作Service实现
 * @createDate 2023-08-03 16:29:36
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
        implements OrdersService {

}




