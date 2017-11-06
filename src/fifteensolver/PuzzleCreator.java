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

    public  Tile[] puzzle = new Tile[16]; // to raczej bedziemy zmieniac takze nie final
    private ArrayList<Integer> numbersHolder = new ArrayList(16);
    private final int x = 4;
    private final int y = 4;

    public PuzzleCreator() {
        shuffleNumbers();
        int counter = 0;
        for (int i = 0; i < 16; i++) {
            puzzle[i] = new Tile(1, 1, numbersHolder.get(counter));
            System.out.print(puzzle[i].getValue()+" ");
            counter++;
        }
    }

    private void shuffleNumbers() {
        for (int i = 0; i <= 15; i++) {
            getNumbersHolder().add(i);
        }
        Collections.shuffle(getNumbersHolder());

    }

    public void setNumbersHolder(ArrayList<Integer> numbersHolder) {
        this.numbersHolder = numbersHolder;
    }

    public ArrayList<Integer> getNumbersHolder() {
        return numbersHolder;
    }
}
