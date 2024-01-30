package org.example.chaptor06.exam2;

import org.example.chaptor06.exam1.Mutex;

public class BinarySemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource(new BinarySemaphore());

        Thread thread1 = new Thread(sharedResource::sum);
        Thread thread2 = new Thread(sharedResource::sum);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종합계 : " + sharedResource.getSharedValue());
    }
}

class SharedResource {
    private int sharedValue = 0;
    private CommonSemaphore commonSemaphore;

    public SharedResource(CommonSemaphore commonSemaphore) {
        this.commonSemaphore = commonSemaphore;
    }

    public void sum() {
        try {
            commonSemaphore.acquired();
            for (int i = 0; i < 10000000; i++) {
                sharedValue++;
            }
        } finally { // 오류가 날수도 있기 때문에 finally로 처리해줘야함.
            commonSemaphore.release();
        }
    }

    public int getSharedValue() {
        return sharedValue;
    }
}
