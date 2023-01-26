public class sudoku {


    private static final int GRID_SIZE = 9;


    public static void main(String[] args) {


        int [][] board = {
                //this is board
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        System.out.println("------------------question-----------------");
        printBoard(board);


        System.out.println();

        if(solveBoard(board)) {
            System.out.println("------------------solved--------------------");
        }else {
            System.out.println("------------------unsolveable-----------------");
        }

        printBoard(board);




    }

    private static void printBoard(int[][] board) {

        for(int row = 0; row < GRID_SIZE ; row++){

            if(row % 3 == 0 && row != 0){
                System.out.println("----------------------");
            }
            for(int col = 0 ; col < GRID_SIZE ; col++){
                if(col % 3 == 0 && col != 0){
                    System.out.print("| ");
                }
                System.out.print(board[row][col]+" ");
            }
            System.out.println();
        }

    }



// these two are simple method for the row and col ... check the place is blank or filled ...
    private static boolean isNumberInRow(int [][] board , int n , int r){
        for(int i = 0 ; i < GRID_SIZE ; i++){
            if(board[r][i] == n) return true;
        }
        return false;
    }


    private static boolean isNumberInCol(int [][] board , int n , int c){
        for(int i = 0 ; i < GRID_SIZE ; i++){
            if(board[i][c] == n) return true;
        }
        return false;
    }

    private static boolean isNumberInBox(int [][] board , int n , int r , int c){
        // int the function n is use which is come from the numberToTry method ...

        int localBoxRow = r - r %3;
        int localBoxCol = c - c % 3;

        for(int i = localBoxRow ; i < localBoxRow + 3 ; i ++){
            for(int j = localBoxCol ; j < localBoxCol + 3 ; j ++){
                if(board[i][j] == n) return true;
            }
        }
        return false;
    }

    private static boolean isValidPlace(int [][] board , int n , int r , int c){
        // this function check the number's place is valid or not
        // eg = if number is already filed by the question number it will return false
        return (
                !isNumberInCol(board,n,c) &&
                ! isNumberInRow(board,n,r)) &&
                !isNumberInBox(board,n,r,c);
    }

    private static boolean solveBoard(int [][] board){

        for(int row = 0 ; row < GRID_SIZE ; row++ ){
            for(int col = 0 ; col < GRID_SIZE ; col ++){

                //in case of our blank space - 0
                if(board[row][col] == 0){

                    //all the number are taken form 1 to 9 ...
                    // for all the number it check the number is valid or not for the position

                    for(int numberToTry = 1 ; numberToTry <= GRID_SIZE ; numberToTry++ ){
                        if(isValidPlace(board,numberToTry,row,col)){
                            board[row][col] =numberToTry;

                            // if the taken number solve the position which is blank the ok
                            // otherwise it will backtrack to the same  blank number - 0
                            if(solveBoard(board)) {
                                // recursive function
                                return true;
                            }
                            //if the number is in valid which is put in the first place then we make
                            //row /col = 0 and it will run again ... this is the role of backtracking ...
                            else {
                                // if numberToTry is not valid for the position
                                board[row][col] = 0;
                            }
                        }

                    }
                    return false;
                }

            }
        }

        return true;
    }

}
