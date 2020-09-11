package com.alibab.demo.fegin;

import com.alibab.demo.fegin.impl.ProviderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "cloud-alibaba-provider", fallback = ProviderServiceFallback.class)
public interface ProviderClient {

    @RequestMapping("/provider/hello")
    String hello();

    @RequestMapping("/provider/config")
    String config();

}
