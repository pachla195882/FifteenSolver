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

    protected ArrayList<ArrayList<Integer>> puzzle = new ArrayList(16);
    protected ArrayList<Integer> numbersHolder = new ArrayList(16);
    
    public PuzzleCreator() {
        for(int i=0;i<=15;i++){
            numbersHolder.add(i);
        }
        Collections.shuffle(numbersHolder);
        //tu jeszcze trzeba dodać liczby z holdera do puzzli i już
    }
    
}
