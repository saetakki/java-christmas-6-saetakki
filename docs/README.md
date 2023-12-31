- 이벤트 
  - 공통사항
      - [X] 총 혜택 금액은 할인 금액의 합계 + 증정 메뉴의 가격이다
      - [X] 할인 후 예상 결제 금액은 할인 전 총 주문 금액에서 할인 금액을 뺀 값이다
      - [X] 매 주 일요일과 크리스마스 당일은 총 주문 금액에서 추가로 1000원을 할인한다.
      - [X] 혜택 금액에 따라 이벤트 배지를 부여한다
  - 크리스마스 디데이 할인
      - [X] 12월 1일부터 25일까지만 진행한다
      - [X] 하루 당 100원씩 할인금액이 증가한다 ( price = base - (date-1)*100)
      - [X] 할인은 총주문 금액에서 해당 금액만큼 할인한다
  - 12월 할인
      - [X] 12월 할인 이벤트는 1일부터 31일까지 운영한다
      - 평일
          - [X] 평일은 목요일부터 일요일까지이다
          - [X] 디저트 메뉴를 메뉴 1개당 2023원 할인한다
      - 주말
          - [X] 주말은 금,토일이다
          - [X] 메인 메뉴를 1개당 2023원 할인한다
  - 증정 이벤트
      - [X] 할인 전 총 주문금액이 12만원 이상일 때 샴페인 1개를 증정한다.
  - 유의사항
      - [X] 총 주문 금액이 10000원 이상이어야 이벤트가 적용된다.
      - [X] 음료만 주문할 수 없다
      - [X] 메뉴는 1회 최대 20개까지 주문할 수 있다.
    
- 입력
    - 공통사항
        - [X] 입력을 담당하는 클래스를 별도 구현한다
        - [X] 입력 규칙에 어긋난 입력이 들어왔을 경우 에러 메세지를 출력 후 재입력 받을 수 있어야한다
    - 날짜 입력
        - [ ] 1 이상 31 이하의 숫자만 입력받는다
    - 주문 입력
        - [X] 메뉴 주문 시 ‘메뉴이름-갯수’ 형식으로 입력받아야 한다
        - [X] 메뉴는 쉼표로 구분한다
      
- 출력
    - 공통사항
        - [X] 출력을 담당하는 클래스를 별도 구현한다.
        - [X] 모든 에러 메세지는 [ERROR] 접두사로 시작하여야 한다

    - 날짜 출력
        - [X] 12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!) 라는 출력 문구를 띄운 후 입력받는다.
        - [X] 1이상 31이하의 숫자가 아닌 경우 “[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.” 라고 출력해야한다.
        - [X] 문자열이 입력된 경우에도 동일한 메세지를 출력해야한다.
    - 주문 출력
        - [X] 주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1) 라는 출력 문구를 띄운 후 입력받는다
        - [X] 메뉴판에 없는 메뉴를 입력할 경우 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.”라는 에러 메세지를 출력한다
        - [X] 음료만 주문한 경우 음료만 상기 문구를 출력한다
        - [X] 한번에 주문 갯수가 20개를 초과할 시 상기 문구를 출력한다
        - [X] 메뉴를 1개 이상 주문하지 않은 경우에도 상기 문구를 출력한다
        - [X] 메뉴 형식이 예시와 같지 않을 경우 상기 문구를 출력한다.
        - [X] 중복 메뉴를 입력한 경우(e.g. 시저샐러드-1,시저샐러드-1) 상기 문구를 출력한다
        - [X] 주문 메뉴를 출력할 수 있어야한다
    - 이벤트 관련 출력
        - [X] 총 주문 금액이 10,000원 이상부터 이벤트가 적용된다는 메세지를 출력해야한다.
        - [X] 음료만 주문할 수 없다는 메세지를 출력해야한다.
        - [X] 메뉴는 한 번에 20개까지 주문할 수 있다는 메세지를 출력해야한다.
        - [X] 총 혜택 금액에 따라 이벤트 배지 이름을 다르게 보여줄 수 있어야한다 없는 경우 ‘없음’을 출력한다
        - [X] 총혜택 금액을 출력할 수 있어야 한다
        - [X] 할인 전 총 주문 금액을 출력할 수 있어야한다
        - [X] 증정 메뉴를 출력할 수 있어야 한다 없을 시 ‘없음’을 출력한다.
        - [X] 혜택 내역을 출력할 수 있어야 한다 없을 시 ‘없음’을 출력한다.
        - [X] 할인 후 예상 결제 금액을 출력할 수 있어야 한다.
        - [X] 적용된 이벤트 내역만 출력한다
      
- validation
    - [X] 1 이상 31이하 숫자가 입력되었는지 검출할 수 있어야한다.
    - [X] 주문 입력이 ‘메뉴-갯수’ 형식으로 입력되었는지 검출할 수 있어야한다.
    - [X] 입력한 메뉴가 존재하는지 검출할 수 있어야한다.
    - [X] 메뉴의 갯수가 1 미만이거나 20 이상인지 검출할 수 있어야한다.
    - [X] 메뉴가 중복으로 입력되었는지 검출할 수 있어야한다
    - [X] 음료만 주문했는지 검출할 수 있어야한다