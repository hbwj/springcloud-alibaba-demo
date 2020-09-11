package com.alibab.demo.controller;

import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider")
@RefreshScope
public class ProvideController {

   @Value("${user.name}")
    private String name;

    @Value("${user.age}")
    private String age;

    @GetMapping("/hello")
    public String hello(){
        return "hello springcloud alibaba";
    }

    @GetMapping("/config")
    public String config(){
      return "user.name:"+name+",user.age:"+age;
    }

}
