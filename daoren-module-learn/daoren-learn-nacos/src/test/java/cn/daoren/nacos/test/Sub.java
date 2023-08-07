package cn.daoren.nacos.test;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;

public class Sub {
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serviceName = NacosConstant.SERVICE_HELLO;
        final NamingService namingService = NamingFactory.createNamingService(NacosConstant.NACOS_ADDRESS);
        namingService.subscribe(serviceName, event -> {
            if (event instanceof NamingEvent) {
                System.out.println("subscribe data: ");
                System.out.println(((NamingEvent) event).getInstances());
            }
        });
        System.out.println("subscribe complete");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
