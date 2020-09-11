package com.alibab.demo.controller;

import com.alibab.demo.fegin.ProviderClient;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/consume")
public class ConsumeController {

    @Autowired
    private ProviderClient providerClient;

    @GetMapping("/hello")
    //注解方式实现限流熔断降级 也可用控制台
    @SentinelResource(value = "hello",fallback = "fallback",blockHandler = "blockHandler")
    public String consume(){

        return  providerClient.hello();
    }

    @GetMapping("/config")
    public String config(){

        return  providerClient.config();
    }



    public String fallback() {
        return "fallback===============" ;
    }


    public String blockHandler( BlockException e) {
        e.printStackTrace();
        return "blockHandler================" ;
    }

    @Bean
    private  void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource("hello");
        // set limit qps to 20
        rule1.setCount(1);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }
}
