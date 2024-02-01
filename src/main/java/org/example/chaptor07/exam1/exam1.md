### synchronized 키워드의 재 진입성

- 모니터 내에서 이미 synchronized 영역에 들어간 스레드가 다시 같은 모니터 영역으로 들어갈 수 있는 것을 말한다.
- 재 진입 가능하다는 것은 락의 획득이 호출 단위가 아닌 스레드 단위로 일어난다는 것을 의미하며, 이미 락을 획득한 스레드는 같은 락을 얻기 위해 대기할 필요가 없다.

```java
class Parent {
    public synchronized void method() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    @Override
    public synchronized void method() {
        System.out.println("Child method: before calling super");
        super.method();
        System.out.println("Child method: after calling super");
    }
}
```

- 위와 같이 상속하게되면 자식은 부모와 동일한 락을 가지게 된다.
- 동기화 된 메서드에서 다른 동기화 된 메서드를 호출하는 경우 <br>
  이미 락(lock)을 가지고 있는 스레드가 같은 락을 확보하고 재진입 시 데드락이 발생하지 않고, 정상적으로 진행할 수 있게 된다.

### 가시성

- synchronized는 가시성을 지원한다.
- 가시성이란 한 스레드가 공유자원을 수정하거나 쓰기 작업을 했을때 다른 스레드가 수정한 내용이 보이는 것을 말한다.<br>
  (CPU의 성능 향상을 위해 메모리에 바로 저장하는게 아닌, 캐시 메모리에 저장하는데, 이게 무조건 캐시메모리의 데이터가 메인 메모리에 반영될거라는 보장이 없다.)
- volatile을 통해 가시성을 보장할 수 있다.

### 그 외 특징

- sleep( )을 실행한 스레드는 동기화 영역에서 대기중이라도 락을 놓거나 해지하지 않는다.
- synchronized의 동기화 영역에 진입하지 못하고 대기중인 스레드는 인터럽트 되지 않는다. (wait 상태)
- synchronized의 동기화 영역에 진입하지 못하고 대기중인 스레드가 다시 경쟁해서 모니터를 획득하는 것은 순서가 정해져 있지 않음. (비공정성)