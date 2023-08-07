package cn.daoren.seata.service;

import cn.daoren.seata.domain.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author pengda
 * @description 针对表【product】的数据库操作Service
 * @createDate 2023-08-03 16:33:57
 */
public interface ProductService extends IService<Product> {

    /**
     * 减库存
     *
     * @param productId : 商品ID
     * @param amount    : 减库存数
     * @author peng_da
     * @since 2023/8/3 16:43
     */
    void reducsProuct(Long productId, Integer amount);
}
