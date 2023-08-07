package cn.daoren.demo.userservice.controller;

import cn.daoren.demo.userservice.api.*;
import cn.daoren.demo.userservice.properties.CustomProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class UserDemoController {

    @Autowired
    private CustomProperties properties;
    @Autowired
    private UserServiceGrpc.UserServiceBlockingStub userService;

    @GetMapping("/get")
    public String get(int id) {
        final UserGetRequest request = UserGetRequest.newBuilder().setId(id).build();
        final UserGetResponse response = userService.get(request);
        return response.getName();
    }

    @PostMapping("/create")
    public int create(String name, int gender) {
        final UserCreateRequest request = UserCreateRequest.newBuilder().setName(name).setGender(gender).build();
        final UserCreateResponse response = userService.create(request);
        return response.getId();
    }

    @GetMapping("/service")
    public String service() {
        return properties.getServiceName() + ":" + properties.getServicePort();
    }
}
