package cn.daoren.demo.userservice.grpc;

import cn.daoren.demo.userservice.api.*;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

/**
 * @author pengda
 */
@Service
public class UserServiceGrpcImpl extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void get(UserGetRequest request, StreamObserver<UserGetResponse> responseObserver) {
        final UserGetResponse.Builder builder = UserGetResponse.newBuilder();
        builder.setId(request.getId())
                .setName("nick Name" + request.getId())
                .setGender(request.getId() % 2 + 1);
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void create(UserCreateRequest request, StreamObserver<UserCreateResponse> responseObserver) {
        // 创建响应对象
        UserCreateResponse.Builder builder = UserCreateResponse.newBuilder();
        builder.setId((int) (System.currentTimeMillis() / 1000));
        // 返回响应
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
