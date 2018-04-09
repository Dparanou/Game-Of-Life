package gameoflife;

public class GameOfLife {
    private int n,m;
    private Grid grid;
    private int table[][];

    public GameOfLife(int n, int m) {
        this.n = n;
        this.m = m;
        table = new int[n][n];
        grid = new Grid(n,m);
    }

    public void start() {
        table = grid.getGrid();
        //grid.showGrid(table);
        grid.nextGen(table);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        //Check if the user give right input
        do {
            if (!checkOddOrEven(n,m)) {
                System.out.println("The 2 parameters must be odd or even both");
            }
            else {
                GameOfLife game = new GameOfLife(n,m);
            }
        }
        while (!checkOddOrEven(n,m));
    }


     public static boolean checkOddOrEven(int n, int m){
        if ((n % 2 == 0 && m % 2 == 0) || (n % 2 == 1 && m % 2 == 1)) {
            return true;
        }
        else {
            return false;
        }
    }
}
