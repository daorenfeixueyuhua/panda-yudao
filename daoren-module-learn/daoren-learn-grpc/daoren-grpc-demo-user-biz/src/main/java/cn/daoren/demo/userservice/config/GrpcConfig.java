package cn.daoren.demo.userservice.config;

import cn.daoren.demo.userservice.api.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengda
 */
@Configuration
public class GrpcConfig {
    public static final Integer GRPC_PORT = 888;

    @Bean
    public ManagedChannel userGrpcManagedChannel() {
        return ManagedChannelBuilder.forAddress("daoren", GRPC_PORT)
                .usePlaintext().build();
    }

    @Bean
    public UserServiceGrpc.UserServiceBlockingStub userService() {
        final ManagedChannel managedChannel = this.userGrpcManagedChannel();
        return UserServiceGrpc.newBlockingStub(managedChannel);
    }
}
