package gameoflife;

public class Cell {
    private int col, row, neighbour;
    private boolean alive;

    public Cell(int col, int row) {
        this.col = col;
        this.row = row;
        neighbour = 0;
        alive = false;
    }

    public int getCol(int col) {
        return col;
    }

    public int getRow(int row) {
        return row;
    }

    public int getNeighbour(int neighbour) {
        return neighbour;
    }

    public boolean getAlive(boolean alive) {
        return alive;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setNeighbour(int neighbour) {
        this.neighbour = neighbour;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
