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
    public static final Color COLOR_SELECTED = Color.orange.brighter();
    public static final Color COLOR_UNSELECTED = Color.orange;
    public Color color = COLOR_UNSELECTED;
    
    public Hadamard(int bit) {
        this.bit = bit;
    }
    
    public void execute(QState state) {
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
            state.states[i] = a.plus(b).times(Math.pow(2, -0.5));
            state.states[i + bitval] = a.minus(b).times(Math.pow(2, -0.5));
        }
    }

    public void reverseExecute(QState state) {
        this.execute(state);
    }

    public static void execute(QState state, int bit) {
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
            state.states[i] = a.plus(b).times(Math.pow(2, -0.5));
            state.states[i + bitval] = a.minus(b).times(Math.pow(2, -0.5));
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
}