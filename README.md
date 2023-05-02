
# 커피숍 주문 시스템

## 요구사항
- 커피숍 주문 시스템을 구현 
- API 제공 
  - 커피 메뉴 목록 조회 API 
  - 포인트 충전하기 API 
  - 커피 주문, 결제하기 API 
  - 인기메뉴 목록 조회 API
  

## 구현 내용
- h2 인메모리 데이터 베이스에 JPA 이용하여 기능 구현
- 모듈 분리(menu(메뉴), order(주문), point(포인트), common(공통)) 
- 각 도메인 추출(메뉴, 주문, 주문상세, 사용자, 포인트)
  - 주문과 메뉴
    - 주문 - 주문상세 (1:N)
    - 주문상세 - 메뉴 (N:1)
    - 하나의 주문은 여러개의 메뉴를 포함(반대도 마찬가지)
  - 사용자와 포인트
    - 사용자에 포인트를 분리하여 관리
    - 1:1 관계
- 도메인 테스트 코드 작성
- 인수 테스트 코드 작성 


## 추가 구현 내용
- 트래픽이 많은 상황을 대비해 '커피 메뉴 목록 조회 API' Embedded Redis를 활용하여 로컬 캐시 적용하여 요청을 최소화(만료 시간 : 5분)
- Reids RestTemplate 사용해 주문 내역 인기 메뉴 관리
  - Redis와 Schedule을 이용한 동시성 제어
  - Redis RedisTemplate 사용한 Hash Key/Value 적용
      - RedisTemplate increment 메서드를 사용해 Key 에 대한 Value 증가
      - Key : 메뉴ID, Value : 주문횟수
      - Embedded Schedule 사용
        - Redis -> DB로 배치 수행 (5초)
  - 일주일 주기 삭제 작업 수행
    - popular_coffee_menu 테이블 데이터 삭제
    - Redis POPULAR_MENU 키 제거 


## 관계도
![relation](https://user-images.githubusercontent.com/58737008/235567296-2ca9bdfd-dd8a-4387-b146-2bad2e38a356.png)

<br/>

## API
### 1. 커피 메뉴 목록 조회 API

#### Request
```bash
curl -X GET http://localhost:8080/coffee-menu/list
```

#### Response
```json
[
  {
    "id": 1,
    "name": "아이스 아메리카노",
    "price": 4500
  },
  {
    "id": 2,
    "name": "아이스 바닐라라떼",
    "price": 5500
  },
  {
    "id": 3,
    "name": "아이스 카라멜마끼야또",
    "price": 5500
  },
  {
    "id": 4,
    "name": "콜드 브루",
    "price": 4500
  },
  {
    "id": 5,
    "name": "아이스티",
    "price": 3500
  }
]
```

 <br/>


### 2. 포인트 충전 하기 API 
#### Request
```bash
curl -X PATCH \
-H "Content-Type: application/json" \
-d '{"userId": 2, "point": 3000}' \
http://localhost:8080/point/charge  
```
| Name   | Type      | Description  | Required |
|:-------|:----------|:-------------|:---------|
| userId | `Long`    | 사용자 ID       | O        |
| point  | `Integer` | 충전 금액        | O        |


#### Response
```json
{
  "userId": 2,
  "point": 23000
}
```

 <br/>

### 3. 커피 주문/결제 하기 API 
#### Request
```bash
curl -X POST \
-H "Content-Type: application/json" \
-d '{"userId": 2, "menuId": 3}' \
http://localhost:8080/order  
```
| Name   | Type     | Description | Required |
|:-------|:---------|:------------|:---------|
| userId | `Long`   | 사용자 ID      | O        |
| menuId  | `Long`   | 메뉴 ID       | O        |


#### Response
```json
{
  "orderId": 2,
  "userId": 2,
  "menuId": 3,
  "totalPrice": 5500,
  "balancePoint": 15000,
  "orderDateTime": "2023-05-02T13:17:45.192112"
}
```
<br />

### 4. 인기메뉴 목록 조회 API
#### Request
```bash
curl -X GET http://localhost:8080/coffee-menu/popular/list
```
#### Response
```json
[
  {
    "menuId": 2,
    "name": "아이스 바닐라라떼",
    "price": 5500,
    "orderCount": 2
  },
  {
    "menuId": 1,
    "name": "아이스 아메리카노",
    "price": 4500,
    "orderCount": 1
  },
  {
    "menuId": 3,
    "name": "아이스 카라멜마끼야또",
    "price": 5500,
    "orderCount": 1
  }
]
```