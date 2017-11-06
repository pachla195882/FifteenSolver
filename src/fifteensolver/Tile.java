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
public class Tile {

    private short x;
    private short y;
    private short value;

    Tile(short x, short y, short value) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    short getX() {
        return x;
    }

    void setX(short x) {
        this.x = x;
    }

    short getY() {
        return y;
    }

    void setY(short y) {
        this.y = y;
    }

    short getValue() {
        return value;
    }

    void setValue(short value) {
        this.value = value;
    }
}
