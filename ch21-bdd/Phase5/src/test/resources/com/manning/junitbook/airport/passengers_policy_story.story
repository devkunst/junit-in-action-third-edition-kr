Meta: 승객 정책
      회사는 승객 유형과 항공편 유형에 따라 항공편에 승객을 추가하거나 삭제하는 정책을 결정한다

Narrative:
회사는
승객과 항공편을 관리해야 한다
회사의 승객 관리 정책은 다음과 같다

Scenario: 이코노미 항공편에서 일반 승객에 관한 정책
Given 이코노미 항공편에서
When 일반 승객은
Then 이코노미 항공편에서 추가가 가능하고 삭제도 가능하다
And 이코노미 항공편에 일반 승객을 중복해서 추가할 수 없다

Scenario: 이코노미 항공편에서 VIP 승객에 관한 정책
Given 이코노미 항공편에서
When VIP 승객은
Then 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다
And 이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없다

Scenario: 비즈니스 항공편에서 일반 승객에 관한 정책
Given 비즈니스 항공편에서
When 일반 승객은
Then 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능하다


Scenario: 비즈니스 항공편에서 VIP 승객에 관한 정책
Given 비즈니스 항공편에서
When VIP 승객은
Then 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능하다
And 비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없다

Scenario: 프리미엄 항공편에서 일반 승객에 관한 정책
Given 프리미엄 항공편에서
When 일반 승객은
Then 프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능하다

Scenario: 프리미엄 항공편에서 VIP 승객에 관한 정책
Given 프리미엄 항공편에서
When VIP 승객은
Then 프리미엄 항공편에서 추가가 가능하고 삭제도 가능하다
And 프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없다
