import java.util.Scanner;

public class SearchAlg{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Current line of white space
        int BRowi = - 1; 

        // Final line of white space
        int BRowf = - 1; 

        // Number of inversions of the initial board
        int Invi = 0;   
        // Number of inversions of the final board
        int Invf = 0;

        // Initial board configuration
        int Bi[] = new int[16]; 
        for(int i = 0; i < 16; i++){
            Bi[i] = sc.nextInt();
            if(Bi[i] == 0){
                if(i % 4 == 0) BRowi = i/4;
                else BRowi = i/4 + 1;
            }
        }

         // Counting the number of inversions in the initial state of the board
        for(int i = 0; i < 16; i++){        
            for(int j = i + 1; j < 16; i++){
                if(Bi[j] < Bi[i]) Invi++;
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

        // Counting the number of inversions in the final state of the board
        for(int i = 0; i < 16; i++){          
            for(int j = i + 1; j < 16; i++){
                if(Bf[j] < Bf[i]) Invf++;
            }
        }
        
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

        }

        }
}
