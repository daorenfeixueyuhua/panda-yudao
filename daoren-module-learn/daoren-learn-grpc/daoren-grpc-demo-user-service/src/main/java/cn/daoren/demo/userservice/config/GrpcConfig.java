package cn.daoren.demo.userservice.config;

import cn.daoren.demo.userservice.grpc.UserServiceGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengda
 */
@Slf4j
@Configuration
public class GrpcConfig {

    public static final Integer GRPC_PORT = 888;

    @Bean
    @SneakyThrows
    public Server grpcServer(final UserServiceGrpcImpl userServiceGrpc) {
        final Server server = ServerBuilder.forPort(GRPC_PORT)
                .addService(userServiceGrpc)
                .build();
        server.start();
        log.info("[grpcServer] 启动成功，端口为{}", GRPC_PORT);
        return server;
    }

}
