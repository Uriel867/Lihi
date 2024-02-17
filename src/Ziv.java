import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ziv implements Runnable {

    private int[] nums;
    private Lock lock = new ReentrantLock();
    final int PRIME_FLAG = -1;

    public Ziv(int[] m) {
        this.nums = m;
    }

    @Override
    public void run() {
        for (int i = 2; i < nums.length; i++) {
            int num = getNum(i);
            if (isPrime(num))
                setPrime(i);
        }
    }

    private synchronized void setPrime(int index) {
        nums[index] = PRIME_FLAG;
    }

    private int getNum(int index) {
        lock.lock();
        try {
            return nums[index];
        }
        finally {
            lock.unlock();
        }
    }

    private boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}
