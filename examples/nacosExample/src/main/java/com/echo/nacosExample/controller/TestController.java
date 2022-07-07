package com.echo.nacosExample.controller;

import com.echo.nacosExample.HTestConfig;
import com.echo.nacosExample.TestConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    final
    TestConfig testConfig;

    final
    HTestConfig hTestConfig;

    @Value("#{${user.names}}")
    private Map<String, String> names;

    public TestController(TestConfig testConfig, HTestConfig hTestConfig) {
        this.testConfig = testConfig;
        this.hTestConfig = hTestConfig;
    }

    @GetMapping("get")
    public Object getConfig(){

        return  names;
    }

}
