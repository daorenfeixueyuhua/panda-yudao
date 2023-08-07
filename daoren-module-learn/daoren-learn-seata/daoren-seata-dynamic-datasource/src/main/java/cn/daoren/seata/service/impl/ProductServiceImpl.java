package cn.daoren.seata.service.impl;

import cn.daoren.seata.domain.entity.Product;
import cn.daoren.seata.mapper.ProductMapper;
import cn.daoren.seata.service.ProductService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pengda
 * @description 针对表【product】的数据库操作Service实现
 * @createDate 2023-08-03 16:33:57
 */
@Slf4j
@DS("storage-ds")
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

    /**
     * @param productId : 商品ID
     * @param amount    : 减库存数
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void reducsProuct(Long productId, Integer amount) {

    }
}




