public class HintCreator {

    private final int[] currentBoard;
    public HintCreator(int[] currentBoard) {
        this.currentBoard = currentBoard;
    }

    /*
        Step 1: Move the 1 Tile and the 2 Tile Into Their Final Positions
        Step 2: Move Tiles 3 and 4 Into Setup Positions
        Step 3: Move Tiles 3 and 4 Into Final Position
        Step 4: Move Tiles 5 and 6 Into Final Position
        Step 5: Move Tiles 7 and 8 Into Setup Positions
        Step 6: Move Tiles 7 and 8 Into Final Positions
        Step 7: Move Tiles 13 and 9 Into Setup Positions
        Step 8: Move Tiles 13 and 9 Into Final Positions
        Step 9: Move Tiles 14 and 10 Into Setup Positions
        Step 10: Move Tiles 14 and 10 Into Their Final Positions
        Step 11: Completing the Puzzle
    */
    public String getHint() {
        if(currentBoard[0] != 1 || currentBoard[1] != 2) {
            return "Step 1: Move the 1 Tile and the 2 Tile Into Their Final Positions";
        }else if(currentBoard[2] != 3 || currentBoard[3] != 4){
            if(currentBoard[2] == 4 && currentBoard[6] == 3) {
                return "Step 3: Move Tiles 3 and 4 Into Final Positions";
            }else {
                return "Step 2: Move Tiles 3 and 4 Into Setup Positions";
            }
        }else if(currentBoard[4] != 5 || currentBoard[5] != 6){
            return "Step 4: Move Tiles 5 and 6 Into Final Position";
        }else if(currentBoard[6] != 7 || currentBoard[7] != 8){
            if(currentBoard[6] == 8 && currentBoard[10] == 7) {
                return "Step 6: Move Tiles 7 and 8 Into Final Positions";
            }else {
                return "Step 5: Move Tiles 7 and 8 Into Setup Positions";
            }
        }else if(currentBoard[8] != 9 || currentBoard[12] != 13){
            if(currentBoard[8] == 13 && currentBoard[9] == 9) {
                return "Step 8: Move Tiles 13 and 9 Into Final Positions";
            }else {
                return "Step 7: Move Tiles 13 and 9 Into Setup Positions";
            }
        }else if(currentBoard[9] != 10 || currentBoard[13] != 14){
            if(currentBoard[9] == 14 && currentBoard[10] == 10) {
                return "Step 10: Move Tiles 14 and 10 Into Their Final Positions";
            }else {
                return "Step 9: Move Tiles 14 and 10 Into Setup Positions";
            }
        }else {
            return "Step 11: Come on its easy";
        }
    }

}
