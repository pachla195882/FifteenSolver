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

        String greedySolve = Greed(puzzle);
        System.out.println(greedySolve);
    }

    public static boolean isMoveLegal(int move, int zero) {
        switch (move) {
            case 0:
                if (zero > 3) {
                    return true;
                } else {
                    return false;
                }
            //  moveList = moveList + "U ";

            case 1:
                if (zero == 3 || zero == 7 || zero == 11 || zero == 15) {
                    return false;
                } else {
                    return true;
                }
            //moveList = moveList + "R ";

            case 2:
                if (zero < 12) {
                    return true;
                } else {
                    return false;
                }
            //  moveList = moveList + "D ";

            case 3:
                if (zero == 0 || zero == 4 || zero == 8 || zero == 12) {
                    return false;
                } else {
                    return true;
                }
            //  moveList = moveList + "L ";

        }
        return false;
    }

    public static int calculateAvgDist(int move, PuzzleCreator state, int zero) {
        int Val;
        int movedVal = 0;
        int Dist = 0;
        switch (move) {
            case 0:
                movedVal = zero - 4;
                for (int i = 0; i < 16; i++) {

                    Val = state.puzzle[i].getValue();
                    if (Val == movedVal) {
                        Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - 1 - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4

                    } else if (Val == zero) {
                        Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() + 1 - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4

                    } else {
                        Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4
                    }

                }
            //  moveList = moveList + "U ";

            case 1:
                movedVal = zero + 1;
                for (int i = 0; i < 16; i++) {

                    Val = state.puzzle[i].getValue();
                    if (Val == movedVal) {
                        Dist += Math.abs(state.puzzle[i].getX() - 1 - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4

                    } else if (Val == zero) {
                        Dist += Math.abs(state.puzzle[i].getX() + 1 - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4

                    } else {
                        Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4
                    }

                }
            //moveList = moveList + "R ";

            case 2:
                movedVal = zero + 4;
                for (int i = 0; i < 16; i++) {

                    Val = state.puzzle[i].getValue();
                    if (Val == movedVal) {
                        Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() + 1 - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4

                    } else if (Val == zero) {
                        Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - 1 - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4

                    } else {
                        Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4
                    }

                }
            //  moveList = moveList + "D ";

            case 3:
                for (int i = 0; i < 16; i++) {
                    movedVal = zero - 1;
                    Val = state.puzzle[i].getValue();
                    if (Val == movedVal) {
                        Dist += Math.abs(state.puzzle[i].getX() + 1 - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4

                    } else if (Val == zero) {
                        Dist += Math.abs(state.puzzle[i].getX() - 1 - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4

                    } else {
                        Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4); // POTENTIAL ERROR!!!!!!!!!!! (int) Val/4
                    }

                }

            //  moveList = moveList + "L ";
        }

        System.out.println(" " + Dist);
        return Dist;
    }

    public static boolean notSolved(PuzzleCreator state) {
        int Val;
        for (int i = 0; i < 16; i++) {
            Val = state.puzzle[i].getValue();
            if (state.puzzle[i].getX() != Val % 4 || state.puzzle[i].getY() != (int) Val / 4) {
                return true;
            }
        }
        return false;
    }

    public static String Greed(PuzzleCreator AA) {
        String moveList = " ";
        int Min = 1000;
        //   Calculate_Avg_dist;
        int zeroPos = 0;
        zeroPos = zero(AA);
        int prevMove = 4;
        int bestMove = 4;
        int Avg = 0;
        int totalMoves = 0;
        int checkedMoves = 0;
        float T = System.currentTimeMillis();
        do {

            for (int i = 0; i < 4; i++) {

                if (i > 1) {
                    if (i != prevMove - 2) {
                        if (isMoveLegal(i, zeroPos)) {
                            checkedMoves++;
                            Avg = calculateAvgDist(i, AA, zeroPos);
                            if (Avg < Min) {
                                bestMove = i;
                                Min = Avg;
                            }
                        }
                    }
                } else {
                    if (i != prevMove + 2) {
                        if (isMoveLegal(i, zeroPos)) {
                            checkedMoves++;
                            Avg = calculateAvgDist(i, AA, zeroPos);
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
            moveTile(zeroPos, bestMove, AA);
            zeroPos = zero(AA);
            prevMove = bestMove;
            Min = 1000;

        } while (notSolved(AA));

        T = System.currentTimeMillis() - T;

        return moveList;
    }

    public static int zero(PuzzleCreator state) {
        for (int i = 0; i < 16; i++) {
            if (state.puzzle[i].getValue() == 0) {
                return i;
            }

        }
        return 0;
    }

    public static void moveTile(int zero, int move, PuzzleCreator state) {
        switch (move) {
            case 0:
                state.puzzle[zero].setValue(state.puzzle[zero - 4].getValue());
                state.puzzle[zero - 4].setValue(0);
                break;
            case 1:
                state.puzzle[zero].setValue(state.puzzle[zero + 1].getValue());
                state.puzzle[zero + 1].setValue(0);
                break;
            case 2:
                state.puzzle[zero].setValue(state.puzzle[zero + 4].getValue());
                state.puzzle[zero + 4].setValue(0);
                break;
            case 3:
                state.puzzle[zero].setValue(state.puzzle[zero - 1].getValue());
                state.puzzle[zero - 1].setValue(0);
                break;
        }
    }
}
