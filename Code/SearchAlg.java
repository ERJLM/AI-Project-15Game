import java.util.Scanner;

public class SearchAlg{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Linha Inicial do espaço em branco
        int BRowi = - 1; 

        // Linha Final do espaço em branco
        int BRowf = - 1; 

        // Número de Inversões do tabuleiro inicial
        int Invi = 0;   
        // Número de Inversões do tabuleiro final
        int Invf = 0;

        // Configuração Inicial do tabuleiro
        int Bi[] = new int[16]; 
        for(int i = 0; i < 16; i++){
            Bi[i] = sc.nextInt();
            if(Bi[i] == 0){
                if(i % 4 == 0) BRowi = i/4;
                else BRowi = i/4 + 1;
            }
        }

         // Contar o número de inversões no estado inicial do tabuleiro
        for(int i = 0; i < 16; i++){        
            for(int j = i + 1; j < 16; i++){
                if(Bi[j] < Bi[i]) Invi++;
            }
        }

        sc.nextLine();

        // Configuaração Final do tabuleiro
        int Bf[] = new int[16]; 
        for(int i = 0; i < 16; i++){
            Bf[i] = sc.nextInt();
            if(Bf[i] == 0){
                if(i % 4 == 0) BRowf = i/4;
                else BRowf = i/4 + 1;
            }
        }

        // Contar o número de inversões no estado final do tabuleiro
        for(int i = 0; i < 16; i++){          
            for(int j = i + 1; j < 16; i++){
                if(Bf[j] < Bf[i]) Invf++;
            }
        }
        
        /*  Se esta condição não for obedecida, não será possível ir do estado inicial
         ao estado final do tabuleiro, e vice-versa.
        */
        if(!(((Invi % 2 == 0) == (BRowi % 2 == 1)) == ((Invf % 2 == 0) == (BRowf % 2 == 1)))){
           System.out.println("Não é possível chegar do estado inicial ao estado final do tabuleiro, e vice-versa.");
        }

        /* Se a condição for obedecida podemos chegar do estado inicial 
         ao estado final do tabuleiro, e vice-versa.
         */
        else{
 
        }

        }
}
