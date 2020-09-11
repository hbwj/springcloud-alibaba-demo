package com.alibab.demo.fegin.impl;

import com.alibab.demo.fegin.ProviderClient;
import org.springframework.stereotype.Component;

@Component
public class ProviderServiceFallback implements ProviderClient {
    @Override
    public String hello() {
        return "Remote call to nacos-provider failed!";
    }

    @Override
    public String config() {
        return "Remote call to nacos-provider failed!";
    }

}
