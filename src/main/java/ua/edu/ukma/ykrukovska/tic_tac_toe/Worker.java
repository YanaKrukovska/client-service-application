package ua.edu.ukma.ykrukovska.tic_tac_toe;

public class Worker extends Thread {

    private int id;
    private final Data data;

    public Worker(int id, Data d) {
        this.id = id;
        data = d;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            synchronized (data) {
                if (data.getState() == 1) {
                    data.tic();
                } else if (data.getState() == 2) {
                    data.tac();
                } else {
                    data.toe();
                }
            }
        }
    }
}


