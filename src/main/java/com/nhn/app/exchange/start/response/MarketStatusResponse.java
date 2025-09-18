package com.nhn.app.exchange.start.response;

import com.nhn.app.exchange.start.domain.EngineStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

// 간단한 데이터 객체이므로 record를 사용하면 편리합니다. (Java 16+)
// 일반 class로 만들어도 무방합니다.
@Getter
@Setter
@AllArgsConstructor
@ToString
public class MarketStatusResponse {
    EngineStatus engine_status;
    LocalDateTime opened_at;

}

