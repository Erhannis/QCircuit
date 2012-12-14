/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

import java.text.NumberFormat;

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

    public QState(int bits, boolean init) {
        this.bits = bits;
        states = new Complex[(int)Math.pow(2, bits)];
        if (init) {
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

    /**
     * Performs a measurement, but doesn't affect the state as
     * it should. This assumes the probabilities add up to 1.
     * @return 
     */
    public int[] fakeMeasure() {//System.out.println(this.toString());this.pMeasure()
        double target = QUtils.r.nextDouble();
        double cur = 0;
        for (int i = 0; i < states.length; i++) {
            cur += states[i].normsqr();
            if (cur > target) {
                int[] result = new int[bits];
                for (int j = 0; j < bits; j++) {
                    if ((i & 1) > 0) {
                        result[j] = 1;
                    } else {
                        result[j] = 0;
                    }
                    i = i >> 1;
                }
                return result;
            }
        }
        int[] result = new int[bits];
        return result;
    }

    /**
     * Performs a measurement, and DOES affect the state as
     * it should. This assumes the probabilities add up to 1.
     * @return 
     */
    public int[] trueMeasure() {
        double target = QUtils.r.nextDouble();
        double cur = 0;
        for (int i = 0; i < states.length; i++) {
            cur += states[i].normsqr();
            if (cur > target) {
                initZeros();
                states[0].real = 0;
                states[i].real = 1;
                int[] result = new int[bits];
                for (int j = 0; j < bits; j++) {
                    if ((i & 1) > 0) {
                        result[j] = 1;
                    } else {
                        result[j] = 0;
                    }
                    i = i >> 1;
                }
                return result;
            }
        }
        int[] result = new int[bits];
        return result;
    }
    
    public QState copy() {
        QState result = new QState(this.bits);
        for (int i = 0; i < states.length; i++) {
            result.states[i] = this.states[i].copy();
        }
        return result;
    }
    
    /**
     * Be Careful with this; don't forget that with modest numbers of bits, you can end up with huge numbers of states.
     * @return 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (states.length > 0) {
            sb.append("(" + states[0].toString() + ")|" + QUtils.padLeft(Integer.toBinaryString(0), bits) + ">");
        }
        for (int i = 1; i < states.length; i++) {
            //TODO Provide formatting options
            //sb.append(" + (" + states[i].toString() + ")|" + QUtils.padLeft(Integer.toBinaryString(i), bits) + ">");
            sb.append(" + \n(" + states[i].toString() + ")|" + QUtils.padLeft(Integer.toBinaryString(i), bits) + ">");
        }
        return sb.toString();
    }
    
    /**
     * Returns the compound state of |a>x|b>.
     * @param b
     * @return 
     */
    public QState outerProduct(QState b) {
        QState result = new QState(this.bits + b.bits, false);
        int index = 0;
        for (int i = 0; i < this.bits; i++) {
            for (int j = 0; j < b.bits; j++) {
                result.states[index++] = this.states[i].times(b.states[j]);
            }
        }
        return result;
    }
}
