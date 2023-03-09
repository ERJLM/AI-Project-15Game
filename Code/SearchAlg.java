public class SearchAlg{
    public static void main(String[] args){
        String choice = null;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Type a command (help for 'help'):");
            choice = scan.nextLine();
            if(choice.equals("quit")) break;
            switch (choice) {
                case "info":
                    System.out.println("Realizado por:\n-> 202203859 - Joao Manuel Cardoso Guedes\n-> 202203858 - Eliandro Ricardo João Luís de Melo\n-> 202105587 - Antonio Maria Araujo Pinto dos Santos\n");
                    break;
                case "createNew":
                    System.out.println("Creating a new board...");
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

                    
                    if(Boardi.check() && Boardf.check()){
                        
                     // Number of inversions of the initial board
                    int Invi = Boardi.inversions();
                        
                    // Number of inversions of the final board
                    int Invf = Boardf.inversions();
                        
                    if(!(((Invi % 2 == 0) == (BRowi % 2 == 1)) == ((Invf % 2 == 0) == (BRowf % 2 == 1)))){
                        System.out.println("It is not possible to reach from the initial state to the final state of the board, and vice versa.");
                    }


                    else{
                        Boardi.printBoard();
                        Boardi.nextGulosa();

                    }
                  }
                    break;
                case "help":
                    System.out.println("info - group information\ncreateNew - create a new Board");
                default:
                    System.out.println("Invalid project Command!");
                    break;
            }
        } while (!choice.equals("quit"));
        scan.close();

        }
}
