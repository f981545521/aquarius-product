package cn.acyou.aquarius.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youfang
 * @version [1.0.0, 2020/8/20]
 **/
@RestController
public class TestController {

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return "ok";
    }
}
