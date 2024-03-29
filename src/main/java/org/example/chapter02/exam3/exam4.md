### sleep(0)과 sleep(n)의 의미

- sleep(millis) 메소드는 네이티브 메소드이기 때문에 sleep(millis)을 실행하게 되면, 시스템 콜을 호출하게 되어 유저모드에서 커널모드로 전환된다.
- 다른 스레드에게 명확하게 실행을 양보하기 위함이라면, sleep(0) 보다는 sleep(1)을 사용하도록 한다.

### sleep(0)
- 스레드가 커널모드로 전환 후 스케쥴러는 현재 스레드와 동일한 우선순위(Priority)의 스레드가 있을 경우 실행대기상태 스레드에게 CPU를 할당함으로 컨텍스트 스위칭이 발생한다.
- 만약 우선순위가 동일한 실행대기 상태의 다른 스레드가 없으면, 스케쥴러는 현재 스레드에게 계속 CPU를 할당해서 컨텍스트 스위칭이 없고, 모드 전환만 일어난다.

### sleep(n)
- 스레드가 커널모드로 전환 후 스케쥴러는 조건에 상관없이 현재 스레드를 대기상태에 두고 다른 스레드에게 CPU를 할당함으로 모든 전환과 함께 컨텍스트 스위칭이 발생한다.
