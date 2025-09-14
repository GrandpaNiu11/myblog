package com.minzheng.blog.controller;

import cn.hutool.core.util.IdUtil;
import com.minzheng.blog.constant.Constant;
import com.minzheng.blog.dto.PhotoBackDTO;
import com.minzheng.blog.util.RedPackageUtil;
import com.minzheng.blog.vo.PageResult;
import com.minzheng.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.concurrent.TimeUnit;

@RestController
public class RedPackageController {
    @Autowired
    RedisTemplate redisTemplate;

    //发红包
    @GetMapping("/send/{totalMoney}/{redpackageNumber}")
    public Result<String> semdRedPackage(@PathVariable int totalMoney, @PathVariable int redpackageNumber) {
        Integer[] integers = RedPackageUtil.splitRedPackageAlSearchAlgorithm(totalMoney, redpackageNumber);
        String key = IdUtil.simpleUUID();
        redisTemplate.opsForList().leftPushAll(Constant.RED_PACKAGE_KEY + key, integers);
        redisTemplate.expire(Constant.RED_PACKAGE_KEY + key, 1, TimeUnit.DAYS);

        return Result.ok(key);
    }

    //抢红包
    @GetMapping("/rob1/{redPackageKey}")
    public Result redPackage(@PathVariable String redPackageKey, @PathParam("phone") String phone) {
        Object redPackage = redisTemplate.opsForHash().get(Constant.RED_PACKAGE_CONSUME_KEY + redPackageKey, phone);
        if (null == redPackage) {
            //执行
            Object o = redisTemplate.opsForList().leftPop(Constant.RED_PACKAGE_KEY + redPackageKey);
            if (o != null) {
                redisTemplate.opsForHash().put(Constant.RED_PACKAGE_CONSUME_KEY + redPackageKey, phone, o);
                return Result.ok(o);
            } else {
                return Result.ok("红包已枪完");
            }
        }
        return Result.ok("不能重复抢");
    }

}
