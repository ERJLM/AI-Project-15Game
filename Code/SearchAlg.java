import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

class Board{
    int Board[][] = new int[4][4];
    int InitialBoard[] = new int[16];
    Stack<Board> SBoard = new Stack<Board>();
    Stack<Board> SAux = new Stack<Board>();
    List<Board> LBoard = new ArrayList<Board>();
    int g = 0;
    int holeRow = -1;
    int holeCol = -1;

    Board(int[] B){
        for(int i = 0, c = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
               Board[i][j] = B[c];
               InitialBoard[c] = B[c++];
               if(Board[i][j] == 0){
                holeRow = i;
                holeCol = j;
               }
            }
          }
    }

    Board(int[][] B){
        for(int i = 0, c = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
               Board[i][j] = B[i][j];
               InitialBoard[c++] = B[i][j];
               if(Board[i][j] == 0){
                holeRow = i;
                holeCol = j;
               }
            }
          }
    }

    List<Board> list(){
        return LBoard;
    }

    int[][] array(){
        return Board;
    }

    Board copy(){
        return new Board(Board);
    }

    int equals(Board B){
        int res = -1;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
               if(Board[i][j] == B.array()[i][j]) res = 0;
               else res = -1;
            }
          }
        return res;
    }

    int nextDepth(Stack<Board> s1, Stack<Board> s2){
       Board B = copy();
       s1.push(B);
       s2.push(B);
       boolean check = false;
       Board BoardR = new Board(Board);
       Board BoardU = new Board(Board);
       Board BoardD = new Board(Board);
       Board BoardL = new Board(Board);

       if(holeRow + 1 < 4){
        BoardR.swap("right");
        s1.push(BoardR);
        check = true;
      }
      if(holeCol + 1 < 4){
       BoardU.swap("up");
       s1.push(BoardU);
       check = true;
     }
     if(holeCol - 1 >= 0){
       BoardD.swap("down");
        if (s1.search(BoardD) == -1) check = false;
        else{
           check = true;
           s1.push(BoardD);
        }
     }
     if(holeRow - 1 >= 0){
       BoardL.swap("left");
       s1.push(BoardL);
       check = true;
     }
     if (!check) return -1;
     nextDepth(s1, s2);
     return 0;
    }

     int ManhattanDist() {
        int mDist = 0;
        for (int x = 0; x < 4; x++) // x-dimension, traversing rows (i)
            for (int y = 0; y < 4; y++) { // y-dimension, traversing cols (j)
                int value = array()[x][y]; // tiles array contains board elements
                if (value != 0) { // we don't compute MD for element 0
                    int targetX = (value - 1) / 4; // expected x-coordinate (row)
                    int targetY = (value - 1) % 4; // expected y-coordinate (col)
                    int dx = x - targetX; // x-distance to expected coordinate
                    int dy = y - targetY; // y-distance to expected coordinate
                    mDist += Math.abs(dx) + Math.abs(dy); 
                } 
            }
        return mDist;
    }
    void nextGulosa(){
       Board BoardR = new Board(Board);
       Board BoardU = new Board(Board);
       Board BoardD = new Board(Board);
       Board BoardL = new Board(Board);
       int Mdist = ManhattanDist();
       System.out.println(Mdist);
       String s = "";
       int moves = 0;
       list().add(copy());
       if(holeCol + 1 < 4){
         BoardR.swap("right");
         int d = BoardR.ManhattanDist();
         if( d < Mdist && !list().contains(BoardR)){
              s = "right";
              Mdist = d;
         }
       }
       if(holeRow - 1 >= 0){
        BoardU.swap("up");
        int d = BoardU.ManhattanDist();
        if( d < Mdist && !list().contains(BoardU)){
             s = "up";
             Mdist = d;
        }
      }
      if(holeRow + 1 < 4){
        BoardD.swap("down");
        int d = BoardD.ManhattanDist();
        if( d < Mdist && !list().contains(BoardD)){
             s = "down";
             Mdist = d;
        }
      }
      if(holeCol - 1 >= 0){
        BoardL.swap("left");
        int d = BoardL.ManhattanDist();
        if( d < Mdist && !list().contains(BoardL)){
             s = "left";
             Mdist = d;
        }
      }
      if(s.equals("")){
        System.out.println("Solution not found");
        return;
      }
      swap(s);
      moves++;
      if(ManhattanDist() == 0){
        System.out.println("Solution found in " + moves + " passos");
        return;
      }
      print();
      nextGulosa();
      list().clear();
    }

    void swap(String move){
    if(move.equals("up")){
        Board[holeRow][holeCol] = Board[holeRow - 1][holeCol];
        Board[holeRow - 1][holeCol] = 0;
        holeRow--;
    }
    else if(move.equals("down")){
        Board[holeRow][holeCol] = Board[holeRow+1][holeCol];
        Board[holeRow+1][holeCol] = 0;
        holeRow++;
    }
    else if(move.equals("left")){
        Board[holeRow][holeCol] = Board[holeRow][holeCol-1];
        Board[holeRow][holeCol-1] = 0;
        holeCol--;
    }
    else if(move.equals("right")){
        Board[holeRow][holeCol] = Board[holeRow][holeCol+1];
        Board[holeRow][holeCol+1] = 0;
        holeCol++;
    }
 }

    int inversions(){
        int Inv = 0;
        for(int i = 1; i < 16; i++){        
            for(int j = i + 1; j < 16; j++){
                //System.out.println(InitialBoard[i] + " j" + InitialBoard[j]);
                if((InitialBoard[i] > InitialBoard[j]) && InitialBoard[j] != 0) Inv++;
                //System.out.println(Inv + "Inv");
            }
        }
        return Inv;
    }

    public void print() {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
              System.out.print(Board[i][j] + " ");
            }
            System.out.println("");
          }
     }
}

public class SearchAlg{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Current line of white space
        int BRowi = - 1; 

        // Final line of white space
        int BRowf = - 1; 

        // Initial board configuration
        int Bi[] = new int[16]; 
        for(int i = 0; i < 16; i++){
            Bi[i] = sc.nextInt();
            if(Bi[i] == 0){
                if(i % 4 == 0) BRowi = i/4;
                else BRowi = i/4 + 1;
            }
        }
        

        sc.nextLine();

        // Final board configuration
        int Bf[] = new int[16]; 
        for(int i = 0; i < 16; i++){
            Bf[i] = sc.nextInt();
            if(Bf[i] == 0){
                if(i % 4 == 0) BRowf = i/4;
                else BRowf = i/4 + 1;
            }
        }

        Board Boardi = new Board(Bi);
        Board Boardf = new Board(Bf);
          
         // Number of inversions of the initial board
             int Invi = Boardi.inversions();   
         // Number of inversions of the final board
             int Invf = Boardf.inversions();
        /*  If this condition is not obeyed, it will not be possible to go from the initial state
          to the final state of the board, and vice versa.
        */
        if(!(((Invi % 2 == 0) == (BRowi % 2 == 1)) == ((Invf % 2 == 0) == (BRowf % 2 == 1)))){
           System.out.println("It is not possible to reach from the initial state to the final state of the board, and vice versa.");
        }

        /* If the condition is obeyed we can arrive from the initial state
          to the final state of the board, and vice versa.
         */
        else{
           /* check all the possible subs, choose which sub has the lowest Manhattan distance plus the cost of reaching the current state from the initial state*/
           // Or the same method but with inversions instead of the MD(choose the fastest)
           Boardi.print();
           Boardi.nextGulosa();

        }

        }
}
