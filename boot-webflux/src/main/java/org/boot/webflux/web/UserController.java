package org.boot.webflux.web;

import org.boot.webflux.entity.User;
import org.boot.webflux.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author luoliang
 * @date 2018/9/8
 * 通过Rest API创建Web Flux
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/users")
    public Flux<String> all() {
        return userService.getAll();
    }


    @GetMapping("/test")
    public Flux<User> test() {
        long longstart = System.currentTimeMillis();

        Mono<Long> mono = Mono.delay(Duration.ofSeconds(2));

        mono.subscribe(n -> {
            System.out.println("生产数据源："+ n);
            System.out.println("当前线程ID："+ Thread.currentThread().getId() + ",生产到消费耗时："+ (System.currentTimeMillis() - longstart));
        });

        mono.subscribe(n -> {
            System.out.println("生产数据源1："+ n);
            System.out.println("当前线程ID1："+ Thread.currentThread().getId() + ",生产到消费耗时："+ (System.currentTimeMillis() - longstart));
        });
        Disposable disposable = Mono.delay(Duration.ofSeconds(2)).subscribe(n -> {
            System.out.println("生产数据源："+ n);
            System.out.println("当前线程ID："+ Thread.currentThread().getId() + ",生产到消费耗时："+ (System.currentTimeMillis() - longstart));
        });
        System.out.println("主线程"+ Thread.currentThread().getId() + "耗时："+ (System.currentTimeMillis() - longstart));
        while(!disposable.isDisposed()) {
        }

        return null;

    }

    @PostMapping("/add")
    public Mono<Boolean> register(@RequestBody User user) {
        return userService.add(user.getId(), user.getName());
    }

    @PostMapping("/find")
    public Mono find(@RequestBody User user) {
        return userService.find(user.getName(), user.getPassword());
    }
}
