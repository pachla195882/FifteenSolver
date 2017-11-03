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

    private final int[][] puzzle = new int[4][4];
    private ArrayList<Integer> numbersHolder = new ArrayList(16);
    private final int X = 4;
    private final int Y = 4;

    public PuzzleCreator() {
        fillPuzzle(puzzle);
    }

    private int[][] fillPuzzle(int[][] puzzle) {
        fillNumbersHolder();
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                puzzle[i][j] = numbersHolder.get(counter);
                counter++;
            }
        }
        return puzzle;
    }

    public void printPuzzle() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    void fillNumbersHolder() {
        for (int i = 0; i <= 15; i++) {
            getNumbersHolder().add(i);
        }
    }

    void shufflePuzzle() {
        Collections.shuffle(getNumbersHolder());
        fillPuzzle(puzzle);

    }

    public void setNumbersHolder(ArrayList<Integer> numbersHolder) {
        this.numbersHolder = numbersHolder;
    }

    public ArrayList<Integer> getNumbersHolder() {
        return numbersHolder;
    }
}
