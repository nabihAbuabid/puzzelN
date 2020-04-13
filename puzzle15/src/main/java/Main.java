import java.util.InputMismatchException;
import java.util.Scanner;


enum OPTIONS {
    RESTART(1),
    RIGHT(2),
    LEFT(3),
    UP(4),
    DOWN(5),
    QUIT(6);

    private final int option;

    OPTIONS(int option) {
        this.option = option;
    }

    public int getValue() {
        return option;
    }

    public static OPTIONS valueOf(int option) {
        switch (option) {
            case 1:
                return RESTART;
            case 2:
                return RIGHT;
            case 3:
                return LEFT;
            case 4:
                return UP;
            case 5:
                return DOWN;
            case 6:
            default:
                return QUIT;
        }
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Puzzel N, Let's start playing");
        boolean quit = false;
        while (!quit) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Board Dimension or 99 to quit");
            int dimension;
            try {
                dimension = sc.nextInt();
                if (dimension == 99) {
                    quit = true;
                    continue;
                }
            } catch (InputMismatchException ime) {
                continue;
            }
            PuzzelN puzzelN = getBoardTiles(sc, dimension);
            if (puzzelN == null) {
                System.out.println("You've inputted invalid data, let's try again");
                continue;
            }

            if (!puzzelN.isSolvable()) {
                System.out.println("You've inputted an unsolveable puzzle, please try again");
                continue;
            }

            //start moving tiles
            boolean restart = false;
            while (!quit && !restart && !puzzelN.isDone()) {
                puzzelN.print();
                OPTIONS option = displayMenu(sc);
                switch (option) {
                    case RESTART:
                        restart = true;
                        break;
                    case RIGHT:
                        handleMove(OPTIONS.RIGHT, sc, puzzelN);
                        break;
                    case LEFT:
                        handleMove(OPTIONS.LEFT, sc, puzzelN);
                        break;
                    case UP:
                        handleMove(OPTIONS.UP, sc, puzzelN);
                        break;
                    case DOWN:
                        handleMove(OPTIONS.DOWN, sc, puzzelN);
                        break;
                    case QUIT:
                        quit = true;
                    default:
                        break;
                }
                if (puzzelN.isDone()) {
                    System.out.println("Puzzle Solved, Great Work !!!!!!");
                    puzzelN.print();
                }
            }
        }
        System.out.println("Have a good day");
    }

    private static void handleMove(OPTIONS option, Scanner sc, PuzzelN puzzelN) {
        try {
            System.out.println("enter tile indices (row, col) to move " + option.toString());
            int row = sc.nextInt();
            int col = sc.nextInt();

            switch (option) {
                case LEFT:
                    puzzelN.moveTileLeft(row, col);
                    break;
                case RIGHT:
                    puzzelN.moveTileRight(row, col);
                    break;
                case UP:
                    puzzelN.moveTileUp(row, col);
                    break;
                case DOWN:
                    puzzelN.moveTileDown(row, col);
                    break;
                default:
                    break;
            }
        } catch (InputMismatchException ime) {
            return;
        }
    }

    /***
     * Get input fropm user and create a board
     * @param sc - System.in scanner
     * @return Board instance if inputs are valid otherwise null
     */
    private static PuzzelN getBoardTiles(Scanner sc, int dimension) {
        try {
            int numOfTiles = dimension * dimension;
            int tiles[] = new int[numOfTiles];
            System.out.println(String.format("Enter %d numbers from 1 to %d, Designate blank tile as 0", numOfTiles, numOfTiles - 1));
            sc.nextLine();
            for (int i = 0; i < numOfTiles; i++) {
                tiles[i] = sc.nextInt();
            }
            //Input is valid, create the Board
            PuzzelN puzzelN = new PuzzelN(dimension);
            puzzelN.fill(tiles);
            return puzzelN;
        } catch (InputMismatchException ime) {
            return null;
        }

//        return null;
    }

    /***
     * Display Menu option and get user choice. if user choice is invalid it
     * will show the menu again till valid choice is selected
     * @param sc - System.in scanner
     * @return returns user choice
     */
    private static OPTIONS displayMenu(Scanner sc) {
        int option = 0;
        while (!(option >= 1 && option <= 6)) {
            System.out.println("\t\t1. Start new game\n\t\t2. move right\n\t\t3. move left\n\t\t4. move up\n\t\t5. move down \n\t\t6. Quit");
            option = sc.nextInt();
            sc.nextLine();
            if (!(option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6))
                System.out.println("Invalid choice");
        }
        return OPTIONS.valueOf(option);
    }
}
