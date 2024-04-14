Meta: 보너스 포인트 정책
      회사는 승객 유형과 마일리지에 따라 보너스 포인트를 계산하는 정책을 결정한다

Narrative:
회사는
보너스 포인트를 관리하고자 한다
회사의 보너스 포인트 관리 정책은 다음과 같다

Scenario: 일반 승객에 대한 보너스 포인트 정책
Given 마일리지와 일반 승객이 있는 상황에서
When 일반 승객이 가지고 있는 마일리지가 <mileage1>과 <mileage2>와 <mileage3>일 때
Then 일반 승객의 보너스 포인트는 <points>가 된다

Examples:
| mileage1 | mileage2 | mileage3| points |
|     349  |     319  |    623  |     64 |
|     312  |     356  |    135  |     40 |
|     223  |     786  |    503  |     75 |
|     482  |      98  |    591  |     58 |
|     128  |     176  |    304  |     30 |

Scenario: VIP 승객에 대한 보너스 포인트 정책
Given 마일리지와 VIP 승객이 있는 상황에서
When VIP 승객이 가지고 있는 마일리지가 <mileage1>과 <mileage2>와 <mileage3>일 때
Then VIP 승객의 보너스 포인트는 <points>가 된다

Examples:
| mileage1 | mileage2 | mileage3| points  |
|     349  |     319  |    623  |     129 |
|     312  |     356  |    135  |      80 |
|     223  |     786  |    503  |     151 |
|     482  |      98  |    591  |     117 |
|     128  |     176  |    304  |      60 |