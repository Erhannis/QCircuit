/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

import java.awt.Color;
import java.util.Arrays;

/**
 * CNot.  The first bit is the one to be modified, the others are the controls.
 * If there is only one bit, CNot becomes Not.
 * Bits are 0-indexed.
 * @author erhannis
 */
public class CNot implements IQGate {
    public int[] bits;
    public int bitcount;
    public static final Color COLOR_SELECTED = Color.orange.brighter();
    public static final Color COLOR_UNSELECTED = Color.orange;
    public Color color = COLOR_UNSELECTED;
    
    /**
     * Note: bitcount is really only used for toMatrix and inverse.
     * @param bits
     * @param bitcount 
     */
    public CNot(int[] bits, int bitcount) {
        this.bits = bits;
        this.bitcount = bitcount;
    }
    
    public void execute(QState state) {
        if (bits.length == 0) {
            return;
        } else if (bits.length == 1) {
            // Augh, have to swap 2^-bitlength of all the state units.
            int bit = bits[0];
//            int max = 1 << (state.bits - bits.length);
//            for (int i = 0; i < max; i++) {
//                
//            }
            int max = 1 << state.bits;
            int bitval = 1 << bits[0];
            for (int i = 0; i < max; i++) {
                if ((i & bitval) > 0) {
                    i += bitval;
                    if (i >= max)
                        break;
                }
                state.swapUnits(i, i + bitval);
            }
        } else {
//            int max = 1 << (state.bits - bits.length);
//            for (int i = 0; i < max; i++) {
//                
//            }
            int max = 1 << state.bits;
            int targetbitval = 1 << bits[0];
            int[] bitvals = new int[bits.length];
            for (int i = 0; i < bitvals.length; i++) {
                bitvals[i] = 1 << bits[i];
            }
            Arrays.sort(bitvals);
            for (int i = 0; i < max; i++) {
                for (int j = 0; j < bitvals.length; j++) {
                    if ((i & bitvals[j]) == 0) {
                        i += bitvals[j];
                        if (i >= max)
                            break;
                    }
                }
                if (i >= max)
                    break;
                state.swapUnits(i, i - targetbitval);
            }
        }
    }

    public void reverseExecute(QState state) {
        this.execute(state, bits);
    }
    
    public static void execute(QState state, int[] bits) {
        if (bits.length == 0) {
            return;
        } else if (bits.length == 1) {
            // Augh, have to swap 2^-bitlength of all the state units.
            int bit = bits[0];
//            int max = 1 << (state.bits - bits.length);
//            for (int i = 0; i < max; i++) {
//                
//            }
            int max = 1 << state.bits;
            int bitval = 1 << bits[0];
            for (int i = 0; i < max; i++) {
                if ((i & bitval) > 0) {
                    i += bitval;
                    if (i >= max)
                        break;
                }
                state.swapUnits(i, i + bitval);
            }
        } else {
//            int max = 1 << (state.bits - bits.length);
//            for (int i = 0; i < max; i++) {
//                
//            }
            int max = 1 << state.bits;
            int targetbitval = 1 << bits[0];
            int[] bitvals = new int[bits.length];
            for (int i = 0; i < bitvals.length; i++) {
                bitvals[i] = 1 << bits[i];
            }
            Arrays.sort(bitvals);
            for (int i = 0; i < max; i++) {
                for (int j = 0; j < bitvals.length; j++) {
                    if ((i & bitvals[j]) == 0) {
                        i += bitvals[j];
                        if (i >= max)
                            break;
                    }
                }
                if (i >= max)
                    break;
                state.swapUnits(i, i - targetbitval);
            }
        }
    }

    public static void reverseExecute(QState state, int[] bits) {
        execute(state, bits);
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

        if (bits.length == 0) {
            return result;
        } else if (bits.length == 1) {
            // Augh, have to swap 2^-bitlength of all the state units.
            int bit = bits[0];
//            int max = 1 << (state.bits - bits.length);
//            for (int i = 0; i < max; i++) {
//                
//            }
            int max = 1 << bitcount;
            int bitval = 1 << bits[0];
            for (int i = 0; i < max; i++) {
                if ((i & bitval) > 0) {
                    i += bitval;
                    if (i >= max)
                        break;
                }
                result.values[i][i] = new Complex(0,0);
                result.values[i + bitval][i + bitval] = new Complex(0,0);
                result.values[i + bitval][i] = new Complex(1,0);
                result.values[i][i + bitval] = new Complex(1,0);
            }
        } else {
//            int max = 1 << (state.bits - bits.length);
//            for (int i = 0; i < max; i++) {
//                
//            }
            int max = 1 << bitcount;
            int targetbitval = 1 << bits[0];
            int[] bitvals = new int[bits.length];
            for (int i = 0; i < bitvals.length; i++) {
                bitvals[i] = 1 << bits[i];
            }
            Arrays.sort(bitvals);
            for (int i = 0; i < max; i++) {
                for (int j = 0; j < bitvals.length; j++) {
                    if ((i & bitvals[j]) == 0) {
                        i += bitvals[j];
                        if (i >= max)
                            break;
                    }
                }
                if (i >= max)
                    break;
                //System.out.println(MainTest.padLeft(Integer.toBinaryString(i), bitcount));
                result.values[i][i] = new Complex(0,0);
                result.values[i - targetbitval][i - targetbitval] = new Complex(0,0);
                result.values[i - targetbitval][i] = new Complex(1,0);
                result.values[i][i - targetbitval] = new Complex(1,0);
            }
        }

        return result;
    }

    public ComplexMatrix toInverseMatrix() {
        return this.toMatrix();
    }

    public IQGate toInverseGate() {
        return this.copy();
    }

    public IQGate copy() {
        return new CNot(this.bits.clone(), this.bitcount);
    }
}
