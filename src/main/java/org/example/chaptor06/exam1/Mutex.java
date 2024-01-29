package org.example.chaptor06.exam1;

public class Mutex {

    private boolean lock = false;

    public synchronized void acquired() {
        while (lock) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lock = true;
    }

    public synchronized void release() {
        lock = false;
        notify();
    }
}
