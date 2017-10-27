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
public class PuzzleCreator {

    protected int[][] puzzle = new int[4][4];
    protected ArrayList<Integer> numbersHolder = new ArrayList(16);

    public PuzzleCreator() {
        for (int i = 0; i <= 15; i++) {
            numbersHolder.add(i);
        }
        Collections.shuffle(numbersHolder);
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println(" ");
            for (int j = 0; j < 4; j++) {
                puzzle[i][j] = numbersHolder.get(counter);
                counter++;
                System.out.print(puzzle[i][j] + " ");
            }
        }
    }

}
