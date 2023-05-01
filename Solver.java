import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solver {
    Board board;
    TreeMap<Board,Boolean> explored;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board board){
        this.board = board; 
        explored = new TreeMap<>();
    }

    // min number of moves to solve initial board
    public int moves(){
        return 0 ;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
        LinkedList<Board> solution = new LinkedList<Board>();
        PriorityQueue<Board> pq = new PriorityQueue<>();
        pq.add(board);
        explored.put(board, true);

        boolean isReached = false;
        Board current;

        while(!pq.isEmpty() && !isReached){
            current = pq.poll();
            if(current.numberOfMoves > 19){
                System.out.println(current);
            }                

            if(current.isGoal()){
                isReached  = true;
            }else{
                Iterable<Board> neighbors = current.neighbors();
                for(Board b : neighbors){
                    if(!explored.containsKey(b)){
                        explored.put(b, true);
                        pq.add(b);
                    }
                }
            }
        }
        System.out.println(isReached);
        System.out.println("Solved");
        return solution;

    }

    // test client (see below) 
    public static void main(String[] args){

        int[][] puzzle2 = {{1,2,3,4},{5,6,7,8},{11,10,9,12},{15,14,13,0}};
        Board board2 = new Board(puzzle2,15,0);
        Solver solver = new Solver(board2);

        Iterable<Board> solution = solver.solution();
        for(Board b : solution){
            System.out.println(b);
        }
    }



}
