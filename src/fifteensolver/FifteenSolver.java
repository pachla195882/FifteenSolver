/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifteensolver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radek
 */
public class FifteenSolver {

    /**
     * @param args the command line arguments
     */
    static final int X = 20;

    public static void main(String[] args) {

        PuzzleCreator puzzle = new PuzzleCreator();
        puzzle.ShuffleXTimes(X);
        String greedySolve = Greed(puzzle);
        System.out.println(greedySolve);

    }

    public static boolean isMoveLegal(int move, int zero) {
        switch (move) {
            case 0:             //UP
                if (zero > 3) {
                    return true;
                } else {
                    return false;
                }

            case 1:             //RIGHT
                if (zero == 3 || zero == 7 || zero == 11 || zero == 15) {
                    return false;
                } else {
                    return true;
                }

            case 2:             //DOWN
                if (zero < 12) {
                    return true;
                } else {
                    return false;
                }

            case 3:             //LEFT
                if (zero == 0 || zero == 4 || zero == 8 || zero == 12) {
                    return false;
                } else {
                    return true;
                }

        }
        System.out.println("Error: illegal move proposed");
        return false;
    }

    public static int calculateAvgDist(PuzzleCreator state) {
        int Dist = 0;
        int Val;
        for (int i = 0; i < 16; i++) {
            Val = state.puzzle[i].getValue();
            Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4);
        }
        return Dist;
    }

    public static int WrongTilesNum(PuzzleCreator state) {
        int Dist = 0;
        int Val;
        for (int i = 0; i < 16; i++) {
            Val = state.puzzle[i].getValue();
            if (Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4) != 0) ;
            Dist++;
        }
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

    public static boolean wasVisited(PuzzleCreator state, List<PuzzleCreator> visitedStates) {

        for (int i = 0; i < visitedStates.size(); i++) {
            for (int j = 0; j < 16; j++) {
                if (visitedStates.get(i).puzzle[j].getValue() != state.puzzle[j].getValue()) {
                    break;
                }
                if (j == 15) {
                    return true;
                }

            }

        }
        return false;
    }

    public static String Greed(PuzzleCreator actualState) {
        //  String moveList = " ";

        //   Calculate_Avg_dist;
        //  int memoryDepth = 70000;
        List<PuzzleCreator> visitedStates = new ArrayList();
        List<Integer> ToExpand = new ArrayList();
        // ArrayList<PuzzleCreator> checkedStates = new ArrayList();
        //int zero(actualState);
        //  int fallBackCounter = 0;
        //  int prevMove = 4;
        //  int bestMove;
        // float avgMinOverLastXMoves = 0;
        int XSize = 10000;
        int Ticker = 0;
        int Avg = 5;
        int totalMoves = 0;
        int checkedMoves = 0;
        visitedStates.add(new PuzzleCreator(actualState));
        ToExpand.add(0);
//actualState.PrintPuzzle();
        //     System.out.println("-----------------------");
        // visitedStates.add(new PuzzleCreator(AA));
        long T = System.currentTimeMillis();
        do {
            actualState = visitedStates.get(ToExpand.get(0));
            ToExpand.remove(0);
//            // Printing telemetry
//            // System.out.println("Min: " + Min + "   Total: " + totalMoves);
//
//            if (Ticker == XSize) {
//                //   Ticker = 0;
//                //   avgMinOverLastXMoves = avgMinOverLastXMoves / (XSize + 1);
//                System.out.println("AVG:  " + Avg + "   Total: " + totalMoves);
//                actualState.PrintPuzzle();
//                //   avgMinOverLastXMoves = 0;
//            } else {
//                Ticker++;
//                //   avgMinOverLastXMoves += Min[0];
//            }
            //  if (totalMoves % XSize == 0) {
            //       actualState.PrintPuzzle();
            //    }
            // Ticker++; 

//             if (visitedStates.size() == memoryDepth) {
//                visitedStates.remove(0);
//            }
            //   if (fallBackCounter == 0) {
            //     visitedStates.add(new PuzzleCreator(actualState));
            //  totalMoves++;
            //   }
            //Refreshing values----
            //    bestMove = 4;
            //zero(actualState) = zero(actualState);
//            -----
            for (int i = 0; i < 4; i++) {

                if (isMoveLegal(i, zero(actualState))) {
                    PuzzleCreator testState = new PuzzleCreator(actualState);
                    moveTile(zero(testState), i, testState);

                    if (!wasVisited(testState, visitedStates)) {
                        checkedMoves++;

                        Avg = calculateAvgDist(testState);
                        testState.cost = testState.cost / 2; //cost of previous moves modification, can by changed to different value, should be further investigaerd
                        testState.cost += Avg;
                        visitedStates.add(new PuzzleCreator(testState));
                        if (Avg == 0) {
                            break;
                        }
                        if (ToExpand.isEmpty()) {
                            ToExpand.add(visitedStates.size() - 1);
                        } else {
                            for (int p = 0; p < ToExpand.size(); p++) {
                                if (visitedStates.get(ToExpand.get(p)).cost > testState.cost) {
                                    ToExpand.add(p, visitedStates.size() - 1);
                                    break;
                                }
                                if (p == ToExpand.size() - 1) {
                                    ToExpand.add(visitedStates.size() - 1);
                                    break;
                                }

                            }
                        }
                    }
                }

            }
            if (Avg == 0) {
                break;
            }
//            if (bestMove == 4) {
//                System.out.println("ERROR: no bestMove found, going back " + fallBackCounter + " time(s)");
//                //   bestMove = prevMove;
//                totalMoves--;
//
//                actualState = new PuzzleCreator(visitedStates.get(visitedStates.size() - 2 - fallBackCounter));
//                fallBackCounter++;
//                continue;
//            } else {
//                fallBackCounter = 0;
//            }
//             if (bestMove > 1) {
//                prevMove = bestMove - 2;
//            } else {
//                prevMove = bestMove + 2;
//            }
            // PuzzleCreator puz = ;

            // moveTile(zero(actualState), bestMove, actualState);
            //  System.out.println("AA:  "+ moveList);
            //                 AA.PrintPuzzle();
            if ((System.currentTimeMillis() - T) > 30000) {
                System.out.println("Not calculated in 30s");
                break;
            }

        } while (true);

        T = System.currentTimeMillis() - T;
        // visitedStates.get(visitedStates.size() - 1).PrintPuzzle();
        totalMoves = visitedStates.get(visitedStates.size() - 1).moveList.length() / 2;
        return visitedStates.get(visitedStates.size() - 1).moveList + " ; Time: " + T + " ; CheckedStates: " + checkedMoves + " ; TotalMoves: " + (totalMoves - X);
    }

    public static int zero(PuzzleCreator state) {
        for (int i = 0; i < 16; i++) {
            if (state.puzzle[i].getValue() == 0) {
                return i;
            }

        }
        System.out.println("ERRRRRROOOOOORRRRRRR!!!!!!! zero not found!!!");
        return 0;
    }

    public static void moveTile(int zero, int move, PuzzleCreator state) {
        switch (move) {
            case 0:
                state.puzzle[zero].setValue(state.puzzle[zero - 4].getValue());
                state.puzzle[zero - 4].setValue(0);
                state.moveList += "U ";
                break;
            case 1:
                state.puzzle[zero].setValue(state.puzzle[zero + 1].getValue());
                state.puzzle[zero + 1].setValue(0);
                state.moveList += "R ";
                break;
            case 2:
                state.puzzle[zero].setValue(state.puzzle[zero + 4].getValue());
                state.puzzle[zero + 4].setValue(0);
                state.moveList += "D ";
                break;
            case 3:
                state.puzzle[zero].setValue(state.puzzle[zero - 1].getValue());
                state.puzzle[zero - 1].setValue(0);
                state.moveList += "L ";
                break;
        }

    }
}
