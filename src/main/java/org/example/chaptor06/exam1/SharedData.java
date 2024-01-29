package org.example.chaptor06.exam1;

public class SharedData {
    private int sharedValue = 0;
    private Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        try {
            mutex.acquired();
            for (int i = 0; i < 10000000; i++) {
                sharedValue++;
            }
        } finally { // 오류가 날수도 있기 때문에 finally로 처리해줘야함.
            mutex.release();
        }
    }

    public int getSharedValue() {
        return sharedValue;
    }
}
