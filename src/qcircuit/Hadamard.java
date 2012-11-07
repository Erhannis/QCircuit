/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

import java.awt.Color;

/**
 *
 * @author erhannis
 */
public class Hadamard implements IQGate {
    public int bit = 0;
    public int bitcount = 0;
    public static final Color COLOR_SELECTED = Color.orange.brighter();
    public static final Color COLOR_UNSELECTED = Color.orange;
    public Color color = COLOR_UNSELECTED;
    
    /**
     * Note: bitcount is really only used for toMatrix and inverse.
     * @param bit
     * @param bitcount 
     */
    public Hadamard(int bit, int bitcount) {
        this.bit = bit;
        this.bitcount = bitcount;
    }
    
    public void execute(QState state) {
        double s = Math.pow(2, -0.5);
        int max = 1 << state.bits;
        int bitval = 1 << bit;
        for (int i = 0; i < max; i++) {
            if ((i & bitval) > 0) {
                i += bitval;
                if (i >= max) {
                    break;
                }
            }
            Complex a = state.states[i];
            Complex b = state.states[i + bitval];
            state.states[i] = a.plus(b).times(s);
            state.states[i + bitval] = a.minus(b).times(s);
        }
    }

    public void reverseExecute(QState state) {
        this.execute(state);
    }

    public static void execute(QState state, int bit) {
        double s = Math.pow(2, -0.5);
        int max = 1 << state.bits;
        int bitval = 1 << bit;
        for (int i = 0; i < max; i++) {
            if ((i & bitval) > 0) {
                i += bitval;
                if (i >= max) {
                    break;
                }
            }
            Complex a = state.states[i];
            Complex b = state.states[i + bitval];
            state.states[i] = a.plus(b).times(s);
            state.states[i + bitval] = a.minus(b).times(s);
        }
    }

    public static void reverseExecute(QState state, int bit) {
        execute(state, bit);
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSelected(boolean selected) {
        if (selected) {
            this.color = COLOR_SELECTED;
        } else {
            this.color = COLOR_UNSELECTED;
        }
    }

    public ComplexMatrix toMatrix() {
        ComplexMatrix result = ComplexMatrix.identity(1 << bitcount);

        double s = Math.pow(2, -0.5);
        int max = 1 << bitcount;
        int bitval = 1 << bit;
        for (int i = 0; i < max; i++) {
            if ((i & bitval) > 0) {
                i += bitval;
                if (i >= max) {
                    break;
                }
            }
            result.values[i][i] = new Complex(s,0);
            result.values[i + bitval][i] = new Complex(s,0);
            result.values[i][i + bitval] = new Complex(s,0);
            result.values[i + bitval][i + bitval] = new Complex(-s,0);
        }
        
        return result;
    }

    public ComplexMatrix toInverseMatrix() {
        return this.toMatrix();
    }
    
    public IQGate toInverseGate() {
        return this.copy();
    }
    
    /**
     * Doesn't copy color.
     * @return 
     */
    public IQGate copy() {
        return new Hadamard(this.bit, this.bitcount);
    }
}