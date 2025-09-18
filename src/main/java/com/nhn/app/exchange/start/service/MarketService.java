package com.nhn.app.exchange.start.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
@Slf4j
@Service
public class MarketService {

    private final AtomicBoolean isMarketOpen = new AtomicBoolean(false);
    private LocalDateTime openedAt; // 개장 시간을 저장할 변수
    private LocalDateTime closedAt;

    /**
     * 장을 시작하고, 개장 시간을 반환합니다.
     * @return 개장 시각 (LocalDateTime)
     * @throws IllegalStateException 이미 개장된 경우 발생
     */
    public LocalDateTime openMarket() {
        if (isMarketOpen.compareAndSet(false, true)) {
            this.openedAt = LocalDateTime.now(); // 현재 시각을 개장 시간으로 저장
            log.info("거래소가 시작되었습니다. 개장 시각: {}", this.openedAt);
            return this.openedAt;
        } else {
            // "이미 개장됨" 상태를 표현하기 위해 예외를 발생시킵니다.
            // 이 예외는 Controller 단에서 잡아 409 응답으로 변환됩니다.
            throw new IllegalStateException("Market is already open.");
        }
    }

    // ... closeMarket, isMarketOpen 메소드 ...
    public LocalDateTime closeMarket() {
        if (isMarketOpen.compareAndSet(true, false)) {
            this.closedAt = LocalDateTime.now();
            log.info("거래소가 마감되었습니다.");

            /**
             * 오더북에 남아 있는 모든 미체결 주문은 자동으로 취소 처리
             */

            return this.closedAt;
        }else{
            throw new IllegalStateException("Market is already close");
        }
    }

    /**
     * 현재 장이 열려있는지 상태를 반환합니다.
     * 이 메소드는 주문 접수 API 등에서 사용됩니다.
     * @return boolean 장 개장 여부
     */
    public boolean isMarketOpen() {
        return isMarketOpen.get();
    }
}