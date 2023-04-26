import java.util.Scanner;

public class Main {
    public static int checkWinner(char[][] game) {
        int x = 0;
        int o = 0;
        int line1 = 0;
        int line2 = 0;
        int line3 = 0;
        int column1 = 0;
        int column2 = 0;
        int column3 = 0;
        int diagonal1;
        int diagonal2;
        int result = 0;

        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                if (game[i][j] == 'X') {
                    x += 1;
                } else if (game[i][j] == 'O') {
                    o += 1;
                }
            }
        }

        diagonal1 = game[0][0] + game[1][1] + game[2][2];
        diagonal2 = game[0][2] + game[1][1] + game[2][0];

        for (int j = 0; j < game.length; j++) {
            line1 += game[0][j];
            line2 += game[1][j];
            line3 += game[2][j];
            column1 += game[j][0];
            column2 += game[j][1];
            column3 += game[j][2];

            boolean xWins = line1 == 264 || line2 == 264 || line3 == 264 || column1 == 264 || column2 == 264 || column3 == 264 || diagonal1 == 264 || diagonal2 == 264;
            boolean oWins = line1 == 237 || line2 == 237 || line3 == 237 || column1 == 237 || column2 == 237 || column3 == 237 || diagonal1 == 237 || diagonal2 == 237;

            if (xWins) {
                result = 264;
            } else if (oWins) {
                result = 237;
            } else if (x + o == 9) {
                result = 9;
            }
        }
        return result;
    }

    public static void showGame(char[][] game) {
        System.out.printf("""
                ---------
                | %c %c %c |
                | %c %c %c |
                | %c %c %c |
                ---------
                """,
                game[0][0], game[0][1], game[0][2],
                game[1][0], game[1][1], game[1][2],
                game[2][0], game[2][1], game[2][2]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] game;
        game = new char[][] {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

        int line;
        int column;
        int player = 0;
        boolean repeat = false;

        showGame(game);

        while (!repeat) {
            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            } else {
                line = scanner.nextInt();
                column = scanner.nextInt();

                //condition for a valid input
                boolean checkRange = line < 1 || line > 3 || column < 1 || column > 3;

                if (checkRange) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (game[line - 1][column - 1] == 'X' || game[line - 1][column - 1] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (player % 2 == 0) {
                        game[line - 1][column - 1] = 'X';
                    } else {
                        game[line - 1][column - 1] = 'O';
                    }
                    player += 1;
                    showGame(game);
                    int result = checkWinner(game);
                    switch (result) {
                        case 264 -> {
                            System.out.print("X wins");
                            repeat = true;
                        }
                        case 237 -> {
                            System.out.print("O wins");
                            repeat = true;
                        }
                        case 9 -> {
                            System.out.print("Draw");
                            repeat = true;
                        }
                    }
                }
            }
        }
    }
}