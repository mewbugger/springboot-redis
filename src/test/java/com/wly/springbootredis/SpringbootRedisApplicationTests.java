package com.wly.springbootredis;

import com.wly.springbootredis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        //写入一条String数据
        redisTemplate.opsForValue().set("name", "杜兰特");
        //获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    /**
     * 该方法将对象存入redis的时候会将对象的类型自动存入redis，占内存
     * 优化可见RedisStringTest
     */
    @Test
    void testSaveUser() {
        //写入数据
        redisTemplate.opsForValue().set("user:100", new User("布克", 28));
        //获取数据
        User user = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println("user = " + user);
    }



}
