/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifteensolver;

/**
 *
 * @author radek
 */
public class FifteenSolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PuzzleCreator puzzle = new PuzzleCreator();
        puzzle.printPuzzle();
        puzzle.shufflePuzzle();
        puzzle.printPuzzle();

        String greedySolve = Greed(puzzle);

    }

    public static boolean isMoveLegal(int move, PuzzleCreator state) {
        return false;
    }

    public static int calculateAvgDist(int move, PuzzleCreator state) {

        return 0;
    }

    public static boolean isSolved(PuzzleCreator state) {
        return false;
    }

    public static String Greed(PuzzleCreator AA) {
        String moveList = " ";
        int Min = 100;
        //   Calculate_Avg_dist;
        int prevMove = 4;
        int bestMove = 4;
        int Avg = 0;
        int totalMoves = 0;
        int checkedMoves = 0;
        float T = System.currentTimeMillis();
        do {
            for (int i = 0; i < 4; i++) {
                if (isMoveLegal(i, AA)) {
                    if (i > 2) {
                        if (i != prevMove - 2) {
                            checkedMoves++;
                            Avg = calculateAvgDist(i, AA);
                            if (Avg < Min) {
                                bestMove = i;
                                Min = Avg;
                            }
                        }
                    } else {
                        if (i != prevMove + 2) {
                            checkedMoves++;
                            Avg = calculateAvgDist(i, AA);
                            if (Avg < Min) {
                                bestMove = i;
                                Min = Avg;
                            }
                        }

                    }
                }

            }
            totalMoves++;
            switch (bestMove) {
                case 0:
                    moveList = moveList + "U ";
                    break;
                case 1:
                    moveList = moveList + "R ";
                    break;
                case 2:
                    moveList = moveList + "D ";
                    break;
                case 3:
                    moveList = moveList + "L ";
                    break;
            }

            prevMove = bestMove;
            Min = 100;

        } while (isSolved(AA));

        T = System.currentTimeMillis() - T;

        return "sa";
    }
}
