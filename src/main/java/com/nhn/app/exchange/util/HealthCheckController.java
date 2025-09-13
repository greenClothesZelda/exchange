package com.nhn.app.exchange.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//서버가 정상 작동하는지 확인하는 용도의 컨트롤러

@RestController
@RequestMapping
public class HealthCheckController {
    @RequestMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}
