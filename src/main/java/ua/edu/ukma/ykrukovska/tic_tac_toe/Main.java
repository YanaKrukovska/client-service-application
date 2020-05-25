package ua.edu.ukma.ykrukovska.tic_tac_toe;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Data data = new Data();

        Worker worker1 = new Worker(1, data);
        Worker worker2 = new Worker(2, data);
        Worker worker3 = new Worker(3, data);

        worker3.join();
    }
}
