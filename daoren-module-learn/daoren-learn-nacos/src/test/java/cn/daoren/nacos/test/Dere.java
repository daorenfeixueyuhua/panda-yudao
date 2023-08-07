package cn.daoren.nacos.test;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

public class Dere {
    public static void main(String[] args) throws NacosException {
        String serviceName = NacosConstant.SERVICE_HELLO;
        Instance instance = new Instance();
        instance.setIp("daoren");
        instance.setPort(8888);
        instance.setWeight(1.0);
        instance.setClusterName("daoren:8888");
        instance.setEphemeral(true); // 是否临时实例,如果是将使用NamingGrpcClientProxy，否则使用NamingHttpClientProxy
        final NamingService namingService = NamingFactory.createNamingService(NacosConstant.NACOS_ADDRESS);
        namingService.deregisterInstance(serviceName, instance);
    }
}
