package cn.daoren.nacos.test;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

public class Pub {
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serviceName = NacosConstant.SERVICE_HELLO;
        Instance instance = new Instance();
        instance.setIp("daoren");
        instance.setPort(8888);
        instance.setWeight(1.0);
        instance.setClusterName("daoren:8888");
        instance.setEphemeral(true); // 是否临时实例,如果是将使用NamingGrpcClientProxy，否则使用NamingHttpClientProxy
        final NamingService namingService = NacosFactory.createNamingService(NacosConstant.NACOS_ADDRESS);
        namingService.registerInstance(serviceName, Constants.DEFAULT_GROUP, instance);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
