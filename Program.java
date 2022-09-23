public class Program {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,8,12,9,10,7,15,13,14,11,0};
        int[] solvable1 = {13,2,10,3,1,12,8,4,5,0,9,6,15,14,11,7};
        int[] solvable2 = {13,2,10,3,1,12,8,4,5,9,6,7,15,14,11,0};
        //new FifteenNumbersPuzzle(arr);
        new FifteenNumbersPuzzle(solvable2);
    }
}
