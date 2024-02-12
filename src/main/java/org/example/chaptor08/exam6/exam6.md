## Condition

```java
/**
 * 현재 스레드가 다음 네 가지 중 하나가 발생할 때까지 대기하게 되며 이 Condition과 관련된 락은 원자적으로 해제된다.
 *  1) 다른 스레드가 이 Condition에 대해 signal 메서드를 호출하고, 현재 스레드가 깨어날 스레드로 선택되는 경우
 *  2) 다른 스레드가 이 Condition에 대해 signalAll 메서드를 호출한 경우
 *  3) 다른 스레드가 현재 스레드를 인터럽트하고 스레드 중단의 인터럽션을 지원하는 경우
 *  4) 의미 없는 깨어남(spurious wakeup)이 발생한 경우
 *  
 * 이 메서드가 반환되기 전에 현재 스레드는 이 Condition과 관련된 락을 다시 획득해야 한다.
 */
void await() throw InterruptedException;
        
// 메서드 호출 시 스레드가 인터럽트 상태로 설정되어 있거나 또는 대기중에 인터럽트 되더라도 시그널을 받을때까지 계속 대기하며 현재 스레드의 인터럽트 상태는 유지된다.
void awaitUninterruptibly();

/**
 * 이 메서드는 지정된 나노초 값에 대한 남은 나노초의 추정치를 반환하거나 시간이 초과된 경우 0보다 작거나 같은 값을 반환한다.
 * 지정된 나노초 시간이 경과할 때까지 대기하도록 한다.
 */
long awaitNanos(long nanosTimeout) throws InterruptedException;

// 지정된 대기 시간이 경과할때까지 대기하도록 한다. 이 메서드는 awaitNanos(uni.toNanos(time)) > 0 같은 동작을 한다.
boolean await(long time, TimeUnit unit) throws InterruptException;

// 지정된 마감 시간이 경과할때까지 대기하도록 한다. 이 메서드의 반환 값은 마감 시간이 경과했는지 여부를 나타낸다.
boolean awaitUntil(Date deadline) throws InterruptedException;

// 대기 중인 스레드가 있다면 그 중 하나가 깨어난다.
void signal(); // 얘를 선호한다.

// Condition에 대해 대기 중이라면 모든 대기 중인 스레드가 깨어난다.
void signalAll();
```

### signalAll() 보다 signal()을 활용하라
- Condition에서 신호를 알릴때 signalAll()보다 signal()을 사용하는 것이 다중 조건을 다루는 더욱 효과적인 방법일 수 있다.
- 한 개의 Lock 객체에서 생성한 여러 개의 Condition은 특정한 조건에 따라 스레드를 구분해서 관리함으로 미세한 제어를 가능하게 해준다.
- 여러 개의 조건이 있을 때 모든 스레드를 동시에 깨우면 경쟁 상태가 발생할 수 있으나, Condition을 여러개 사용하면 각각의 조건에 대해 필요한 스레드만 깨울 수 있다.

### Condition 사용 시 주의사항
- Condition 객체는 단순한 일반 객체로서 synchronized 문에서 대상으로 사용하거나, 자체 모니터 wait 및 notify 메서드를 호출할 수 있다.
- Condition 객체의 모니터를 사용하는 것은 해당 Condition과 연결된 Lock을 사용하거나 await() 및 signal() 메서드를 사용하는 것과 특정한 관계가 없다.
- 혼동을 피하기 위해 Condition 인스턴스를 이러한 방식으로 사용하지 않는 것이 좋다.