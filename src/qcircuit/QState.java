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
    
    public void allocInitStates() {
        states = new Complex[(int)Math.pow(2, bits)];
        for (int i = 0; i < (int)Math.pow(2, bits); i++) {
            states[i] = new Complex();
        }
    }    
    
    public void initZeros() {
        if (states.length > 0) {
            states[0].real = 1;
            states[0].imag = 0;
        }
        for (int i = 1; i < states.length; i++) {
            states[i].real = 0;
            states[i].imag = 0;
        }
    }
    
    public void swapUnits(int a, int b) {
        Complex bucket = states[a];
        states[a] = states[b];
        states[b] = bucket;
    }
    
    /**
     * Gets the probabilities of each bit, but doesn't affect the state (like
     * it should).
     * @return 
     */
    public double[] pMeasure() {
        double[] result = new double[bits];
        for (int i = 0; i < states.length; i++) {
            double p = states[i].normsqr();
            for (int j = 0; j < bits; j++) {
                if ((i & (1 << j)) > 0) {
                    result[j] += p;
                }
            }
        }
        return result;
    }
    
    /**
     * Gets the probabilities of each bit, but doesn't affect the state (like
     * it should).  Rounds them to 0 or 1.
     * @return 
     */
    public int[] rMeasure() {
        double[] pm = this.pMeasure();
        int[] result = new int[bits];
        for (int i = 0; i < bits; i++) {
            result[i] = (int)(pm[i] + 0.5);
        }
        return result;
    }
    
    public QState copy() {
        QState result = new QState(this.bits);
        for (int i = 0; i < states.length; i++) {
            result.states[i] = this.states[i].copy();
        }
        return result;
    }
}
