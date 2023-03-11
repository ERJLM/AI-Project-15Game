import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Main program function.
 * <p>
 * This method will control the program, calls the search methods and creates game boards.
 *
 * @throws IOException if the provided input is not correct
 */
public class SearchAlg{
    public static void main(String[] args) throws IOException {
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
                case "create":
                    System.out.println("Creating new boards...");

                    System.out.println("\n[ Initial Board ]");
                    System.out.println("Please type 16 spaced integers with a 0 for empty space:");

                    String[] strs = boardIO();
                    if(strs.length != 16) throw new IOException("Incorrect board size given.");

                    int[] a = new int[strs.length];

                    for (int i = 0; i < strs.length; i++) {
                        a[i] = Integer.parseInt(strs[i]);
                    }
                    System.out.println("Accepted.");

                    System.out.println("\n[ Final Board ]");
                    System.out.println("Please type 16 spaced integers with a 0 for empty space:");

                    strs = boardIO();
                    if(strs.length != 16) throw new IOException("Incorrect board size given.");

                    int[] b = new int[strs.length];

                    for (int i = 0; i < strs.length; i++) {
                        b[i] = Integer.parseInt(strs[i]);
                    }
                    System.out.println("Accepted.");


                    Board Boardi = new Board(a);
                    Board Boardf = new Board(b);

                    System.out.println("\nStarting the game...");
                    Game game = new Game(Boardi,Boardf);

                    String choiceGame = null;
                    Scanner scanGame = new Scanner(System.in);
                    do {
                        System.out.println("\nType a algorithm (help for 'help'):");
                        choiceGame = scan.nextLine();
                        if(choiceGame.equals("quit")) break;
                        switch (choiceGame) {
                            case "dfs":
                                game.searchDfs();
                                break;
                            case "astar":
                                game.searchAStar();
                                break;
                            case "astar2":
                                game.searchAStar2();
                                break;
                            case "greedy":
                                game.searchGreedy();
                                break;
                            case "greedy2":
                                game.searchGreedy2();
                                break;
                            case "bfs":
                                game.searchBfs();
                                break;
                            case "dls":
                                game.searchIdfs();
                                break;
                            case "help":
                                System.out.println("dfs - dfs search\nastar - astar search\nastar2 - astar2 search\ngreedy - greedy search\ngreedy2 - greedy2 search\nbfs - bfs search\ndls - dls search");
                            default:
                                System.out.println("Invalid game Command!");
                                break;
                        }
                    } while (!choiceGame.equals("quit"));
                    scan.close();
                    System.out.println("Exiting current game...");
                    break;
                case "help":
                    System.out.println("info - group information\ncreate - create a new Board\nquit - exit the program");
                default:
                    System.out.println("Invalid project Command!");
                    break;
            }
        } while (!choice.equals("quit"));
        scan.close();

    }

    /**
     * Main IO function.
     * <p>
     * This method will control the IO of th program using BufferedReader.
     *
     * @throws IOException if the provided input is not correct
     */
    public static String[] boardIO() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();

        String[] strs = lines.trim().split("\\s+");
        return strs;

    }
}
