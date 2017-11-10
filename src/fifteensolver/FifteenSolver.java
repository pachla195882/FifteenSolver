/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifteensolver;

import java.util.Collections;
import java.util.ArrayList;

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
puzzle.ShuffleXTimes(100);
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
        System.out.println("Error: illegal move proposed");
        return false;
    }

    public static int calculateAvgDist(PuzzleCreator state) {
        int Dist = 0;
        int Val;
        for (int i = 0; i < 16; i++) {
            Val = state.puzzle[i].getValue();
            if(Val!=0)
            Dist += Math.abs(state.puzzle[i].getX() - Val % 4) + Math.abs(state.puzzle[i].getY() - (int) Val / 4);
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

    public static boolean wasVisited(PuzzleCreator state, ArrayList<PuzzleCreator> visitedStates) {

        for (int i = 0; i < visitedStates.size(); i++) {
            for (int j = 0; j < 16; j++) {
                if (visitedStates.get(i).puzzle[j].getValue() != state.puzzle[j].getValue()) {
                    //return false;
                    break;

                }
                if (j == 15) {
                    return true;
                }

            }

        }
        return false;
    }

    public static String Greed(PuzzleCreator AA) {
        String moveList = " ";
        int Min =50;
        //   Calculate_Avg_dist;
        int memoryDepth = 70000;
        ArrayList<PuzzleCreator> visitedStates = new ArrayList();
        int zeroPos;
       int fallBackCounter=0;
        int prevMove = 4;
        int bestMove ;
        float avgMinOverLastXMoves = 0;
        int XSize = 10000;
        int Ticker = 0;
        int Avg ;
        int totalMoves = 0;
        int checkedMoves = 0;
        
           // visitedStates.add(new PuzzleCreator(AA));
        long T = System.currentTimeMillis();
        do {
               // Printing telemetry
           // System.out.println("Min: " + Min + "   Total: " + totalMoves);
           
            if (Ticker == XSize) {
                Ticker = 0;
                avgMinOverLastXMoves = avgMinOverLastXMoves / (XSize+1);
                System.out.println("AVG:  " + avgMinOverLastXMoves + "   Total: " + totalMoves);
                avgMinOverLastXMoves = 0;
            } else {
                Ticker++;
                avgMinOverLastXMoves += Min;
            }
            if(totalMoves%XSize ==0)
                AA.PrintPuzzle();
            // Ticker++; 
            
//             if (visitedStates.size() == memoryDepth) {
//                visitedStates.remove(0);
//            }
             if(fallBackCounter==0){
            visitedStates.add(new PuzzleCreator(AA));
            totalMoves++;
             }
            //Refreshing values----
            bestMove = 4;
             Min = 1000;
               zeroPos = zero(AA);
                
               //-----
            for (int i = 0; i < 4; i++) {

              
                    if (isMoveLegal(i, zeroPos)) {
                        PuzzleCreator Tst = new PuzzleCreator(AA);
                        moveTile(zeroPos, i, Tst);
          
                        if (!wasVisited(Tst, visitedStates)) {
                            checkedMoves++;
                            Avg = calculateAvgDist(Tst);
                            if (Avg < Min) {
                                bestMove = i;
                                Min = Avg;
                            }
                        }
                    }
             

            }
            if (bestMove ==4){
                System.out.println("ERROR: no bestMove found "+fallBackCounter);
             //   bestMove = prevMove;
             totalMoves--;
            
                AA = new PuzzleCreator(visitedStates.get(visitedStates.size()-2-fallBackCounter));
                 fallBackCounter++;
                continue;
            }
            else
                fallBackCounter = 0;
//             if (bestMove > 1) {
//                prevMove = bestMove - 2;
//            } else {
//                prevMove = bestMove + 2;
//            }
             // PuzzleCreator puz = ;
           
           
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
            //  System.out.println("AA:  "+ moveList);
            //                 AA.PrintPuzzle();
          
          if((System.currentTimeMillis() - T)>30000 )
         break;
           

        } while (notSolved(AA));

        T = System.currentTimeMillis() - T;

        return moveList+ " ; Time: "+ T+ " ; CheckedStates: "+ checkedMoves + " ; TotalMoves: "+ totalMoves;
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
