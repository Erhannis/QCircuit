/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

/**
 *
 * @author erhannis
 */
public class QState {
    public int bits = 0;
    public Complex[] states;
    
    public static final boolean INIT_ALL = true;
    
    public QState(int bits) {
        this.bits = bits;
        states = new Complex[(int)Math.pow(2, bits)];
        if (INIT_ALL) {
            for (int i = 0; i < (int)Math.pow(2, bits); i++) {
                states[i] = new Complex();
            }
        }
    }
    
    public void swapUnits(int a, int b) {
        Complex bucket = states[a];
        states[a] = states[b];
        states[b] = bucket;
    }
}
