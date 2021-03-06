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
public final class PuzzleCreator {

    public Tile[] puzzle = new Tile[16];
    private ArrayList<Integer> numbersHolder = new ArrayList(16);
   public int cost = 0;
   public String moveList = "";

    public PuzzleCreator() {
        int counter2 = -1;
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0) {
                counter2++;
            }
            puzzle[i] = new Tile(i - counter2 * 4, counter2, i);
        }
    }

    public void ShuffleXTimes(int X) {
        int zer = 0;
        int move = 0;
        for (int i = 0; i < X; i++) {
            do {
                move = (int) (Math.random() * 4);
            } while (!FifteenSolver.isMoveLegal(move, zer));
            FifteenSolver.moveTile(zer, move, this);
            zer = FifteenSolver.zero(this);
        }

    }

    public PuzzleCreator(PuzzleCreator auxiliaryPuzzle) {

        int counter2 = -1;
        this.cost = auxiliaryPuzzle.cost;
        this.moveList = auxiliaryPuzzle.moveList;

        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0) {
                counter2++;
            }
            puzzle[i] = new Tile(i - counter2 * 4, counter2, 0);

        }
        for (int i = 0; i < 16; i++) {
            this.puzzle[i].setValue(auxiliaryPuzzle.puzzle[i].getValue());
        }
    }

    private void shuffleNumbers() {
        for (int i = 0; i <= 15; i++) {
            getNumbersHolder().add(i);
        }
        Collections.shuffle(getNumbersHolder());

    }

    public void PrintPuzzle() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(puzzle[i * 4 + j].getValue() + " ");
            }
            System.out.println("");
        }
    }

    public void setNumbersHolder(ArrayList<Integer> numbersHolder) {
        this.numbersHolder = numbersHolder;
    }

    public ArrayList<Integer> getNumbersHolder() {
        return numbersHolder;
    }
}
