Feature: 승객 정책
  회사는 승객 유형에 따른 승객 관리 정책을 결정한다

  Scenario Outline: 일반 승객과 관련한 정책
    Given 항공편명이 "<flightNumber>" 이고 좌석 수가 <seats> 인 항공편과 "<file>"에 정의되어 있는 승객 정보가 있는 상황에서
    When 일반 승객은
    Then 항공편에서 삭제할 수 있다
    And 다른 항공편에 추가할 수 있다

    Examples:
      |flightNumber  | seats  | file                       |
      |  AA1234      | 50     | flights_information.csv    |
      |  AA1235      | 50     | flights_information2.csv   |
      |  AA1236      | 50     | flights_information3.csv   |

  Scenario Outline: VIP 승객과 관련한 정책
    Given 항공편명이 "<flightNumber>" 이고 좌석 수가 <seats> 인 항공편과 "<file>"에 정의되어 있는 승객 정보가 있는 상황에서
    When VIP 승객은
    Then 항공편에서 삭제할 수 없다

    Examples:
      |flightNumber  | seats  | file                       |
      |  AA1234      | 50     | flights_information.csv    |
      |  AA1235      | 50     | flights_information2.csv   |
      |  AA1236      | 50     | flights_information3.csv   |
