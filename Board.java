import java.util.Arrays;
import java.util.LinkedList;

public class Board implements Comparable<Board>{
    int[] puzzle;
    int n;
    int zeroLoc;
    int numberOfMoves;
    int manhattanDistance;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles, int zeroLoc, int numberOfMoves){
        n = tiles.length;
        puzzle = new int[n*n];
        for(int i = 0; i < n * n; i++){
            puzzle[i] = tiles[i / n][i % n];
        }
        this.zeroLoc = zeroLoc;
        this.numberOfMoves = numberOfMoves;
        this.manhattanDistance = manhattan();
    }

    public Board(Board board, char command){
        n = board.getSize();
        if(command == 'l'){
            this.puzzle = swap(board.puzzle, board.zeroLoc, board.zeroLoc - 1);
            this.zeroLoc = board.zeroLoc - 1;
        }else if(command == 'r'){
            this.puzzle = swap(board.puzzle, board.zeroLoc, board.zeroLoc + 1);
            this.zeroLoc = board.zeroLoc + 1;
        }else if(command == 'u'){
            this.puzzle = swap(board.puzzle, board.zeroLoc, board.zeroLoc - 4);
            this.zeroLoc = board.zeroLoc - 4;
        }else{
            this.puzzle = swap(board.puzzle, board.zeroLoc, board.zeroLoc + 4);
            this.zeroLoc = board.zeroLoc + 4;
        }
        this.numberOfMoves = board.numberOfMoves + 1;

    }

    public int[] swap(int[] puzzle, int index1, int index2){
        int[] newPuzzle = new int[4 * 4];
        for(int i = 0; i < n * n; i++){
            newPuzzle[i] = puzzle[i]; 
        }

        int temp = newPuzzle[index1];
        newPuzzle[index1] = newPuzzle[index2];
        newPuzzle[index2] = temp;
        return newPuzzle;
    }

    // string representation of this board
    public String toString(){
        String result = getSize() + " - " + numberOfMoves + "\n";
        for(int i = 0; i < getSize() ; i++){
            for(int j = 0; j < getSize(); j++){
                result = result + puzzle[i * n + j] + "-";
            }
            result = result + "\n";
        }
        return result;
    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int col, int row){
        return puzzle[col * n + row];
    }

    // board size n
    public int getSize(){
        return n;
    }

    public int hamming() {
        int res = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                int tile = tileAt(i, j);
                if (tile != 0 && tile != (i * getSize() + j + 1)) {
                    res++;
                }
            }
        }
        return res;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int res = 0;
        int num;
        int coef = 1;
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                num = tileAt(i, j) - 1;
                if(num >= 0){
                    res += coef * (Math.abs(num / getSize() - i) + Math.abs(num % getSize() - j));
                }
            }
        }
        return res;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return Arrays.equals(puzzle, board.puzzle);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(puzzle);
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        LinkedList<Board> neighbors = new LinkedList<>();

        if(zeroLoc / getSize() > 0 && zeroLoc / getSize() < getSize() - 1 && zeroLoc % 4 > 0 && zeroLoc % 4 < getSize() - 1){
            neighbors.add(new Board(this,'l'));
            neighbors.add(new Board(this,'r'));
            neighbors.add(new Board(this,'u'));
            neighbors.add(new Board(this,'d'));
        }else if(zeroLoc / getSize() == 0 && zeroLoc % 4 == 0){
            neighbors.add(new Board(this,'r'));
            neighbors.add(new Board(this,'d'));
        }else if(zeroLoc / getSize() == 0 && zeroLoc % 4 == getSize() - 1){
            neighbors.add(new Board(this,'l'));
            neighbors.add(new Board(this,'d'));
        }else if(zeroLoc / getSize() == getSize() - 1 && zeroLoc % 4 == 0){
            neighbors.add(new Board(this,'r'));
            neighbors.add(new Board(this,'u'));
        }else if(zeroLoc / getSize() == getSize() - 1 && zeroLoc % 4 == getSize() - 1){
            neighbors.add(new Board(this,'l'));
            neighbors.add(new Board(this,'u'));
        }else if(zeroLoc / getSize() == 0){
            neighbors.add(new Board(this,'l'));
            neighbors.add(new Board(this,'r'));
            neighbors.add(new Board(this,'d'));
        }else if(zeroLoc / getSize() == getSize() - 1 ){
            neighbors.add(new Board(this,'l'));
            neighbors.add(new Board(this,'r'));
            neighbors.add(new Board(this,'u'));
        }else if(zeroLoc % 4 == 0){
            neighbors.add(new Board(this,'r'));
            neighbors.add(new Board(this,'u'));
            neighbors.add(new Board(this,'d'));
        }else if(zeroLoc % 4 == getSize() - 1){
            neighbors.add(new Board(this,'l'));
            neighbors.add(new Board(this,'u'));
            neighbors.add(new Board(this,'d'));
        }

        return neighbors;
    }

    // is this board solvable?
    public boolean isSolvable(){
        return false;
    }

    @Override
    public int compareTo(Board o) {
        return this.manhattan() + numberOfMoves - o.manhattan() - o.numberOfMoves;
    }

    // Method to get a copy of the puzzle array
    public int[] getPuzzle() {
        return Arrays.copyOf(puzzle, puzzle.length);
    }

     // unit testing (required)
     public static void main(String[] args){
        int[][] solvable2 = {{13,2,10,3},{1,12,8,4},{5,9,6,7},{15,14,11,0}};
        Board board = new Board(solvable2,15,0);
        System.out.println(board.hamming());
        System.out.println(board.manhattan());
        int[][] puzzle2 = {{1,2,3,4},{5,6,7,8},{9,10,0,12},{13,14,15,11}};
        Board board2 = new Board(puzzle2,10,0);
        System.out.println(board2.hamming());
        System.out.println(board2.manhattan());
        System.out.println(board2);
        Iterable<Board> iter = board2.neighbors();
        for(Board b : iter){
            System.out.println(b);
        }
    }
}
