import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solver {
    private Board initialBoard;
    private HashMap<Board, Board> predecessorMap; // Map to store each board's predecessor

    // Constructor
    public Solver(Board initialBoard){
        this.initialBoard = initialBoard;
        predecessorMap = new HashMap<>();
        predecessorMap.put(initialBoard, null); // Initial board has no predecessor
    }

    // Reconstruct the solution path
    private LinkedList<Board> reconstructPath(Board goalBoard){
        LinkedList<Board> path = new LinkedList<>();
        for(Board board = goalBoard; board != null; board = predecessorMap.get(board)){
            path.addFirst(board); // Add board to the beginning of the list
        }
        return path;
    }

    // Solve the puzzle using the A* algorithm
    public Iterable<Board> solution(){
        PriorityQueue<Board> pq = new PriorityQueue<>();
        pq.add(initialBoard);

        while(!pq.isEmpty()){
            Board currentBoard = pq.poll();

            // Check if current board is the goal
            if(currentBoard.isGoal()){
                return reconstructPath(currentBoard);
            }

            // Process neighboring boards
            for(Board neighbor : currentBoard.neighbors()){
                if(!predecessorMap.containsKey(neighbor)){
                    predecessorMap.put(neighbor, currentBoard); // Set current board as predecessor of neighbor
                    pq.add(neighbor);
                }
            }
        }

        // Return an empty path if the goal is not reachable
        return new LinkedList<>();
    }

    public static void main(String[] args){
        // Define an initial puzzle configuration
        
        
        int[][] initialTiles = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        int[][] solvable2 = {
            {13,2,10,3},
            {1,12,8,4},
            {5,9,6,7},
            {15,14,11,0}};

        // Create the initial board
        Board initialBoard = new Board(solvable2, 15,0);
    
        // Instantiate the solver with the initial board
        Solver solver = new Solver(initialBoard);
    
        // Solve the puzzle and get the solution path
        Iterable<Board> solutionPath = solver.solution();
    
        // Print the solution path
        System.out.println("Solution Path:");
        int stepCount = 0;
        for (Board board : solutionPath) {
            System.out.println("Step " + stepCount + ":");
            System.out.println(board); // Print the board state
            stepCount++;
        }
    
        // Print the total number of moves
        System.out.println("Total number of moves: " + (stepCount - 1));
    }
    
}
