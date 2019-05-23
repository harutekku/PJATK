package GUI3;
class AddingThread extends Thread {

    private Counter c;

    public AddingThread(Counter c) {
        this.c = c;
    }

    public synchronized void run() {
        for (int i = 0; i < 1000; i++) {
            c.inc(i);
        }
    }
}

class Counter {
    private int x = 0;
    void inc(int value) {
        int sum = x + value;
        x = sum;
    }
    public String toString() {
        return x + "";
    }
}
