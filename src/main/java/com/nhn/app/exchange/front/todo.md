이 폴더는 주문의 front-point입니다. 
controller를 이용해서 주문을 받고 service로 넘겨줍니다.
service에서는 OrderQueue에 주문을 넣어줍니다.

cf) OrderQueue는 생산자 소비자 패턴을 이용해서 구현되어야 합니다.