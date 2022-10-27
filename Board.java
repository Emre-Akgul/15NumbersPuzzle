public class Board {
    
    int[][] puzzle;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        puzzle = tiles;
    }

    // string representation of this board
    public String toString(){
        String result = getSize() + "\n";
        for(int i = 0; i < getSize() ; i++){
            for(int j = 0; j < getSize(); j++){
                result = result + puzzle[i][j];
            }
            result = result + "\n";
        }
        return result;
    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int col, int row){
        return puzzle[col][row];
    }

    // board size n
    public int getSize(){
        return puzzle.length;
    }

    // number of tiles out of place
    public int hamming(){
        int res = 0;
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                if(i * getSize() + j == tileAt(i, j) - 1){
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
        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                
                num = tileAt(i, j) - 1;
                if(num >= 0){
                    res += Math.abs(num / getSize() - i) + Math.abs(num % getSize() - j);
                }
                
            }
        }
        return res;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 15;
    }

    // does this board equal y?
    public boolean equals(Board other){

        for(int i = 0; i < getSize(); i++){
            for(int j = 0; j < getSize(); j++){
                if(other.puzzle[i][j] != this.puzzle[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        return null;
    }

    // is this board solvable?
    public boolean isSolvable(){
        return false;
    }   

    // unit testing (required)
    public static void main(String[] args){
        int[][] solvable2 = {{13,2,10,3},{1,12,8,4},{5,9,6,7},{15,14,11,0}};
        Board board = new Board(solvable2);
        System.out.println(board.hamming());
        System.out.println(board.manhattan());
        int[][] puzzle2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        Board board2 = new Board(puzzle2);
        System.out.println(board2.hamming());
        System.out.println(board2.manhattan());
    }
}
