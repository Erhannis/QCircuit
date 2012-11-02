/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

import java.util.Arrays;

/**
 * CNot.  The first bit is the one to be modified, the others are the controls.
 * If there is only one bit, CNot becomes Not.
 * Bits are 0-indexed.
 * @author erhannis
 */
public class CNot implements IQGate {
    public int[] bits;
    
    public CNot(int[] bits) {
        this.bits = bits;
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
}
