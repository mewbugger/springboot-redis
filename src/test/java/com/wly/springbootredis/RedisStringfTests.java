package com.wly.springbootredis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wly.springbootredis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisStringfTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        //写入一条String数据
        stringRedisTemplate.opsForValue().set("name", "王乐岩");
        //获取String数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    //springMVC中默认使用的序列化处理工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testSaveUser() throws JsonProcessingException {
        //创建对象
        User user = new User("杜兰特", 34);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入数据
        stringRedisTemplate.opsForValue().set("user:200", json);
        //获取数据
        String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
        //手动反序列化
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("user = " + user1);
    }

    @Test
    void testHash(){
        //写入数据
        stringRedisTemplate.opsForHash().put("user:300", "name", "小帅");
        stringRedisTemplate.opsForHash().put("user:300", "age", "34");
        //读取数据
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:300");
        System.out.println("entries = " + entries);

    }

}
