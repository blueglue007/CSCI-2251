import java.util.Scanner;


class TicTacToe {

    private int[] board;
    private int X = 1;
    private int Y = 2;
    private int EMPTY = 0;
    private int step = 0;
    private boolean xTurn = true;

    enum decision {
        WIN,DRAW,CONTINUE;
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            switch(board[i]) {
                case 1:
                    System.out.print("X");
                    break;
                case 2:
                    System.out.print("Y");
                    break;
                case 0:
                    System.out.print(" ");
            }
            if (i%3 == 2) {
                System.out.println();
            }
        }

        //System.out.print("-------------");
    }

    public void printStatus() {
        if(gameStatus() == decision.CONTINUE) {
            System.out.print("Continuing");
        } else if (gameStatus() == decision.WIN){
            if(xTurn == true) {
                System.out.print("X win");
            } else {
                System.out.print("Y win");
            }
        } else {
            System.out.print("DRAW");
        }
    }

    public decision gameStatus() {
        if (step == 9) {
            return decision.DRAW;
        }
        int rowCount;
        int colCount;
        for (int i = 0; i < 3; i++) {
            rowCount = board[3*i] + board[3*i+1] + board[3*i+2];
            colCount = board[i] + board[i+3] + board[i+6];
            if (rowCount == 3 || colCount ==3) {
                return decision.WIN;
            } else if (rowCount == 6 || colCount ==6) {
                return decision.WIN;
            }
        }
        return decision.CONTINUE;
    }

    public void TicTacToe() {
        board = new int[9];
        for(int i=0; i<9; i++) {
            board[i] = 0;
        }
    }

    public boolean vaildMove(int x, int y) {
        if(x<0||x>2||y>0||y>2) {
            System.out.println("Invalid move.");
            return false;
        }else if (board[3*(x-1)+y-1] != 0) {
            System.out.println("Invalid move.");
            return false;
        }
        return true;
    }

    public void play() {
        int x,y;
        Scanner scan = new Scanner(System.in);
        while(gameStatus() == decision.CONTINUE) {
            printBoard();

            if (xTurn) {
                System.out.print("Player 1 make a move :");
                x = scan.nextInt();
                y = scan.nextInt();

                while (!vaildMove(x, y)) {
                    System.out.print("PLEASE Enter row and column :");
                    x = scan.nextInt();
                    y = scan.nextInt();
                }
                board[3*(x-1)+y-1] = 1;
                step++;
                xTurn = false;
            } else {
                System.out.print("Player 2 make a move :");
                x = scan.nextInt();
                y = scan.nextInt();

                while (!vaildMove(x, y)) {
                    System.out.print("PLEASE Enter row and column :");
                    x = scan.nextInt();
                    y = scan.nextInt();
                }
                board[3*(x-1)+y-1] = 2;
                step++;
                xTurn = true;
            }
        }

    }
}