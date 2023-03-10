import java.util.List;
import java.util.ArrayList;

public class Board {
    Board parent;
    private int Board[][] = new int[4][4];
    private int InitialBoard[] = new int[16];
    private int g;
    private int holeRow;
    private int holeCol;

    Board(int[] Bi){
        for(int i = 0, c = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
               Board[i][j] = Bi[c];
               InitialBoard[c] = Bi[c++];
               if(Board[i][j] == 0){
                holeRow = i;
                holeCol = j;
               }
            }
          }
          g = 0;
          parent = null;
    }

    Board(String s){
        String[] Bi = s.split(" ");
        for(int i = 0, c = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
               Board[i][j] = Integer.valueOf(Bi[c]);
               InitialBoard[c] = Integer.valueOf(Bi[c]);
               c++;
               if(Board[i][j] == 0){
                holeRow = i;
                holeCol = j;
               }
            }
          }
          g = 0;
          parent = null;
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
            g = 0;
            parent = null;
    }

    boolean check(){
        for(int i = 0; i < 16; i++){
            if(InitialBoard[i] < 0 || InitialBoard[i] > 15){
                System.out.println("This Board is not valid");
                return false;
            }
            for(int j = i + 1; j < 16; j++){
               if(InitialBoard[i] == InitialBoard[j]){
                   System.out.println("This Board is not valid");
                   return false;
               } 
            }
          }
          return true;
    }
   
    Board getParent(){
        return parent;
    }

    void setParent(Board B){
        parent = B;
    }

    int getHoleCol(){
        return holeCol;
    }

    int getHoleRow(){
        return holeRow;
    }

    Board getRight(){
        if(holeCol + 1 < 4){
        Board r = copy();
        r.swap("right");
        return r;
        }
        return null;
    }

    Board getLeft(){
        if(holeCol - 1 >= 0){
        Board l = copy();
        l.swap("left");
        return l;
        }
        return null;
    }

    Board getUp(){
        if(holeRow - 1 >= 0){
        Board u = copy();
        u.swap("up");
        return u;
        }
        return null;
    }

    Board getDown(){
        if(holeRow + 1 < 4){
        Board d = copy();
        d.swap("down");
        return d;
        }
        return null;
    }

    int getG(){
        return g;
    }

    int[][] array(){
        return Board;
    }

    void setG(int x){
        g = x;
    }

    Board copy(){
        Board r = new Board(Board);
        r.setG(getG());
        r.setParent(getParent());
        return r;
    }

    
    boolean equals(Board B){
        return (this.toString().equals(B.toString()));
    }

    //This heuristic counts the number of pieces out of place.
    int heuristic2(Board Goal){
        int h = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
               if(Board[i][j] != Goal.array()[i][j]) h++;
               }
            }
          return h;
    }

    
     int ManhattanDist(Board Goal) {
        int mDist = 0;
        int x1[] = new int[16];
        int y1[] = new int[16];
        int x2[] = new int[16];
        int y2[] = new int[16];

        //Stored the position of the values of each board
        for (int i = 0; i < 4; i++){ 
            for (int j = 0; j < 4; j++) { 
                  x1[Goal.array()[i][j]] = i;
                  y1[Goal.array()[i][j]] = j;
                  x2[array()[i][j]] = i;
                  y2[array()[i][j]] = j;
                } 
            }
           
            //Calculate the distance between the place of a value on the current board and the place of the value on the goal board 
            for (int i = 0; i < 4; i++){ 
                for (int j = 0; j < 4; j++) { 
                   if(array()[i][j] != 0) mDist += Math.abs(x1[array()[i][j]] - x2[array()[i][j]]) + Math.abs(y1[array()[i][j]] - y2[array()[i][j]]);
                    } 
                }
        return mDist;
    }
   

    List<Board> expand(){
        List<Board> l = new ArrayList<Board>();
        Board b = copy();
        if(holeCol + 1 < 4){
            //System.out.println(this);
            b.swap("right");
            l.add(b);
          }
          b = copy();
          if(holeRow - 1 >= 0){
           b.swap("up");
          l.add(b);
         }
         b = copy();
         if(holeRow + 1 < 4){
            b.swap("down");
            l.add(b);
         }
         b = copy();
         if(holeCol - 1 >= 0){
          b.swap("left");
            l.add(b);
         }
        
         return l;
    }
 
    void swap(String move){
    
    if(move.equals("up")){
        parent = copy();
        Board[holeRow][holeCol] = Board[holeRow - 1][holeCol];
        Board[holeRow - 1][holeCol] = 0;
        holeRow--;
        g = getG() + 1;
    }
    else if(move.equals("down")){
        parent = copy();
        Board[holeRow][holeCol] = Board[holeRow+1][holeCol];
        Board[holeRow+1][holeCol] = 0;
        holeRow++;
        g = getG() + 1;
    }
    else if(move.equals("left")){
        parent = copy();
        Board[holeRow][holeCol] = Board[holeRow][holeCol-1];
        Board[holeRow][holeCol-1] = 0;
        holeCol--;
        g = getG() + 1;
    }
    else if(move.equals("right")){
        parent = copy();
        Board[holeRow][holeCol] = Board[holeRow][holeCol+1];
        Board[holeRow][holeCol+1] = 0;
        holeCol++;
        g = getG() + 1;
    }
 }

   public int inversions(){
        int Inv = 0;
        for(int i = 0; i < 16; i++){        
            for(int j = i + 1; j < 16; j++){
                //System.out.println(InitialBoard[i] + " j" + InitialBoard[j]);
                if((InitialBoard[i] > InitialBoard[j]) && InitialBoard[j] != 0) Inv++;
                
            }
        }
        
        return Inv;
    }
 
    @Override
    public String toString() {
        String res = "";
        String s;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Integer a = Board[i][j];
                s = a.toString();
                res += s;
                if( i < 3 || j < 3)res += " ";
            }
          }
          return res;
     }

     public void print(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                 System.out.print(Board[i][j] + " ");
            }
            System.out.println("");
          }
     }
}
