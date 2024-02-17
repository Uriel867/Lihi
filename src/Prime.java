public class Prime {
    private int[] m;//range of numbers
    private Thread[] threads;//number of threads


    public Prime(int m, int n) {
        checkParams(m,n);
        this.m = new int[m + 1];
        this.threads = new Thread[n];
        fillArray();
        instantiateThreads();
    }

    private void instantiateThreads() {
        for (int i = 0; i < threads.length; i++) {
            Ziv z = new Ziv(m);
            threads[i] = new Thread(z);
        }
        startThreads();

    }

    public void startThreads() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        joinThreads();
    }

    public String getResult(){
        String result = "";
        for (int i = 0; i < m.length; i++) {
            if(m[i] == -1){
                result += i + "\t";
            }
        }
        return result;
    }

    private void joinThreads() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkParams(int m,int n){
        if(m <= 0)
            throw new RuntimeException("Illegal number range");
        if(n <= 0)
            throw new RuntimeException("Illegal number of threads");
    }

    private void fillArray(){
        for (int i = 0; i < m.length; i++) {
            m[i] = i;
        }
    }


}
