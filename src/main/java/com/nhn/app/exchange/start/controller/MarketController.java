package com.nhn.app.exchange.start.controller;

import com.nhn.app.exchange.start.domain.EngineStatus;
import com.nhn.app.exchange.start.response.MarketStatusResponse;
import com.nhn.app.exchange.start.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    /**
     * 200 OK: 성공 응답
     */
    @PostMapping("/open")
    public ResponseEntity<MarketStatusResponse> openMarket() {
        LocalDateTime openedAt = marketService.openMarket();

        MarketStatusResponse response = new MarketStatusResponse(
                EngineStatus.RUNNING,
                openedAt
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/close")
    public ResponseEntity<MarketStatusResponse> closeMarket() {
        LocalDateTime closedAt = marketService.closeMarket();

        MarketStatusResponse response = new MarketStatusResponse(
                EngineStatus.STOPPED,
                closedAt
        );
        return ResponseEntity.ok(response);
    }

    /**
     * 409 Conflict: 이미 개장된 경우의 예외 처리
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException ex) {
        // 에러 응답 본문도 일관된 구조를 위해 Map 이나 별도 DTO를 사용합니다.
        Map<String, String> errorResponse = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * 500 Internal Server Error 핸들러 (모든 예외의 최후 처리)
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 상태 코드를 반환하도록 지정
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleGeneralException(Exception ex) {
        // ⚠️ 중요: 실제 서버에서는 에러 로그를 반드시 기록해야 합니다.
        log.error("Unhandled exception occurred", ex);

        // ⚠️ 중요: 사용자에게는 간단하고 안전한 메시지를 반환합니다.
        // ex.getMessage() 등 내부 구현을 노출하지 마세요.
        return Map.of("error", "서버에 예상치 못한 오류가 발생했습니다.");
    }
}