package com.echo.nacosExample.controller;

import com.echo.nacosExample.TestConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    final
    TestConfig testConfig;

    public TestController(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    @GetMapping("get")
    public Object getConfig(){

        return  testConfig;
    }

}
