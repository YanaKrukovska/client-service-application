package ua.edu.ukma.ykrukovska.tic_tac_toe;

public class Data {
    private int state = 1;

    public int getState() {
        return state;
    }

    public void tic() {
        System.out.print("tic-");
        state = 2;
    }

    public void tac() {
        System.out.print("tac-");
        state = 3;
    }

    public void toe() {
        System.out.println("toe");
        state = 1;
    }
}
