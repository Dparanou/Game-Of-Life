package gameoflife;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grid {
    private int[][] table,tableNext;
    private Cell[][] grid;
    private int m, n;
    private Random random = new Random();
    private Generation[][] previousGeneration, nextGeneration;

    public Grid(int n, int m) {
        table = new int[n][n];
        tableNext = new int [n][n];
        this.m = m;
        this.n = n;
        grid = new Cell[n][n];
        previousGeneration = new Generation[n][2];
        nextGeneration = new Generation[n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                previousGeneration[i][j] = new Generation(0, 0);
                nextGeneration[i][j] = new Generation(0, 0);
            }
        }
        makeGrid(n, m);
    }

    //Create a grid nxn and a centered mxm squared region within the grid with randomly generate cells
    public void makeGrid(int n, int m) {
        int diff = (n - m) / 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (diff > i || diff > j || n - diff < i + 1 || n - diff < j + 1) {
                    table[i][j] = 0;
                    grid[i][j] = new Cell(i, j);
                }
                else {
                    int l = random.nextInt(2);
                    table[i][j] = l;
                    grid[i][j] = new Cell(i, j);

                    if (l == 1) {
                        grid[i][j].setAlive(true);
                    }
                }
                //System.out.print(table[i][j]);
            }
            // System.out.println();
        }

        showGrid(table, previousGeneration, nextGeneration);

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Next Generation!!!");
            nextGen(table);
        }
    }

    //Show the grid, with previous and next generation
    public void showGrid(int[][] table, Generation[][] previousGeneration, Generation[][] nextGeneration) {
        for (int i = 0; i < n; i++) {
            System.out.print("[+" + previousGeneration[i][0].getBorn() + "/-" + previousGeneration[i][1].getDeath() + "]");
            for (int j = 0; j < n; j++) {
                if (table[i][j] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print("*");
                }
            }
            System.out.print("[+" + nextGeneration[i][0].getBorn() + "/-" + nextGeneration[i][1].getDeath() + "]");
            System.out.println();
        }

    }

    public int[][] getGrid() {
        return table;
    }

    private String[] possibleNeighbors(int[][] table, int i, int j) {
        String[] orientations = {"N", "NW", "W", "SW", "S", "SE", "E", "NE"};

        if (i == 0)  {
            orientations = Arrays.stream(orientations).filter(s -> !s.contains("N")).toArray(String[]::new);
        }
        if (i = table.length - 1)  {
            orientations = Arrays.stream(orientations).filter(s -> !s.contains("S")).toArray(String[]::new);
        }
        if (j == 0)  {
            orientations = Arrays.stream(orientations).filter(s -> !s.contains("W")).toArray(String[]::new);
        }
        if (j == table.length - 1)  {
            orientations = Arrays.stream(orientations).filter(s -> !s.contains("E")).toArray(String[]::new);
        }

        return orientations;
    }

    private boolean hasNeighbor(int i, int j, String fromWhere) {
        switch (fromWhere) {
            case "NW":
                return table[row - 1][col - 1] == 1;
            case "W":
                return table[row - 1][col] == 1;
            case "SW":
                return table[row - 1][col + 1] == 1;
            case "S":
                return table[row][col - 1] == 1;
            case "SE":
                return table[row][col + 1] == 1;
            case "E":
                return table[row + 1][col - 1] == 1;
            case "NE":
                return table[row + 1][col] == 1;
            case "N":
                return table[row + 1][col + 1] == 1;
            default:
                return false;
        }
    }

    //Count the Neighbors of every cell at the table
    private int countNeighbors(int[][] table, int row, int col) {
        String[] orientations = possibleNeighbors(table, row, col);
        int counter = 0;

        orientations = Arrays.stream(orientations).filter(s -> hasNeighbor(row, col, s)).toArray(String[]::new);

        return orientations.length;
    }

    //Calculate the next Generation, taking into consideration the neighbors of each cell, and compute the death and born of generations
    public void nextGen(int[][] table) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.println("Neighbor of " + i + " " + j + " " + countNeighbors(table, i, j));
                if (countNeighbors(table, i, j) < 2 || countNeighbors(table, i, j) >= 4) {
                    tableNext[i][j] = 0;
                    grid[i][j].setAlive(false);
                    previousGeneration[i][1] = nextGeneration[i][1];
                    nextGeneration[i][1].increaseDeath();
                }
                else {
                    if (table[i][j] == 0){
                        tableNext[i][j] = 1;
                        grid[i][j].setAlive(true);
                        previousGeneration[i][1] = nextGeneration[i][1];
                        nextGeneration[i][1].increaseBorn();
                    }
                }
            }
        }

        tableNext=table;
        showGrid(tableNext, previousGeneration, nextGeneration);
    }
}
