public class Program {
    public static void main(String[] args) {
        // Generate a solvable board
        int[] generatedBoard = BoardGenerator.generateSolvableBoard();

        // Create a new FifteenNumbersPuzzle with the generated board
        new FifteenNumbersPuzzle(generatedBoard);
    }
}
