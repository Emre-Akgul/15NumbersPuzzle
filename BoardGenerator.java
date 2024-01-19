import java.util.Random;

public class BoardGenerator {
    private static final Random random = new Random();
    private static final int N = 4; // Size of the puzzle



    public static int[] generateSolvableBoard() {
        int[][] board = new int[N][N];
        int zeroLoc = initializeBoard(board);
        shuffleBoard(board, zeroLoc, 1000); // Shuffle with 1000 random moves

        return flattenBoard(board);
    }

    private static int initializeBoard(int[][] board) {
        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = count % (N * N);
                count++;
            }
        }
        return N * N - 1; // Initial zero location (blank tile)
    }

    private static void shuffleBoard(int[][] board, int zeroLoc, int moves) {
        int zeroRow = zeroLoc / N;
        int zeroCol = zeroLoc % N;
        for (int i = 0; i < moves; i++) {
            int[] directions = new int[] {-1, 1, -N, N}; // Up, down, left, right
            while (true) {
                int direction = directions[random.nextInt(directions.length)];
                int newRow = zeroRow + direction / N;
                int newCol = zeroCol + direction % N;
                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N) {
                    // Swap zero with the tile in the chosen direction
                    board[zeroRow][zeroCol] = board[newRow][newCol];
                    board[newRow][newCol] = 0;
                    zeroRow = newRow;
                    zeroCol = newCol;
                    break;
                }
            }
        }
    
        // Move the zero tile to the bottom right corner
        while (zeroRow < N - 1) {
            board[zeroRow][zeroCol] = board[zeroRow + 1][zeroCol];
            board[zeroRow + 1][zeroCol] = 0;
            zeroRow++;
        }
        while (zeroCol < N - 1) {
            board[zeroRow][zeroCol] = board[zeroRow][zeroCol + 1];
            board[zeroRow][zeroCol + 1] = 0;
            zeroCol++;
        }
    }

    private static int[] flattenBoard(int[][] board) {
        int[] flattened = new int[board.length * board.length];
        int index = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                flattened[index++] = board[i][j];
            }
        }
        return flattened;
    }
}