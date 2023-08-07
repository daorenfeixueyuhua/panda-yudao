package cn.daoren.test.controller;

import cn.daoren.test.controller.dto.UserDTO;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/list")
    public Flux<UserDTO> listAll() {
        List<UserDTO> result = new ArrayList<>();

        result.add(UserDTO.builder().id(1).name("panda1").build());
        result.add(UserDTO.builder().id(2).name("panda2").build());
        result.add(UserDTO.builder().id(3).name("panda3").build());
        return Flux.fromIterable(result);
    }

    @GetMapping("/get")
    public Mono<UserDTO> get(@RequestParam("id") Integer id) {
        // 查询用户
        UserDTO user = UserDTO.builder().id(1).name("panda1").build();
        // 返回
        return Mono.just(user);
    }

    /**
     * 添加用户
     *
     * @param addDTO 添加用户信息 DTO
     * @return 添加成功的用户编号
     */
    @PostMapping("add")
    public Mono<Integer> add(@RequestBody Publisher<UserDTO> addDTO) {
        // 插入用户记录，返回编号
        Integer returnId = 1;
        // 返回用户编号
        return Mono.just(returnId);
    }

    /**
     * 更新指定用户编号的用户
     *
     * @param updateDTO 更新用户信息 DTO
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public Mono<Boolean> update(@RequestBody Publisher<UserDTO> updateDTO) {
        // 更新用户记录
        Boolean success = true;
        // 返回更新是否成功
        return Mono.just(success);
    }

    /**
     * 删除指定用户编号的用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    @PostMapping("/delete") // URL 修改成 /delete ，RequestMethod 改成 DELETE
    public Mono<Boolean> delete(@RequestParam("id") Integer id) {
        // 删除用户记录
        Boolean success = false;
        // 返回是否更新成功
        return Mono.just(success);
    }
}
