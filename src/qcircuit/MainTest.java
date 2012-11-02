/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

import java.util.Arrays;

/**
 *
 * @author erhannis
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QState state = new QState(4);
        state.initZeros();
        int[] bits = {0, 2};
        
//        int max = 1 << state.bits;
//        int targetbitval = 1 << bits[0];
//        int[] bitvals = new int[bits.length];
//        for (int i = 0; i < bitvals.length; i++) {
//            bitvals[i] = 1 << bits[i];
//        }
//        Arrays.sort(bitvals);
//        for (int i = 0; i < max; i++) {
//            for (int j = 0; j < bitvals.length; j++) {
//                if ((i & bitvals[j]) == 0) {
//                    i += bitvals[j];
//                    if (i >= max) {
//                        break;
//                    }
//                }
//            }
//            if (i >= max) {
//                break;
//            }
//            System.out.println(padLeft(Integer.toBinaryString(i), state.bits));
//        }
        
        //CNot cnot = new CNot();
        for (int i = 0; i < (1 << state.bits); i++) {
            state.initZeros();
            for (int j = 0; j < state.bits; j++) {
                if ((i & (1 << j)) > 0) {
                    CNot.execute(state, new int[] {j});
                }
            }
            System.out.print(toBitString(state.rMeasure()) + " -> ");
            CNot.execute(state, new int[]{2,1,0});
            System.out.println(toBitString(state.rMeasure()));
        }
    }
    
    public static String padLeft(String s, int length) {
        while (s.length() < length) {
            s = "0" + s;
        }
        return s;
    }
    
    public static void printBitString(int[] bits) {
        for (int i = bits.length - 1; i >= 0; i--) {
            System.out.print(bits[i]);
        }
        System.out.println("");
    }

    public static void printBitString(double[] bits) {
        for (int i = bits.length - 1; i >= 0; i--) {
            System.out.print(bits[i]);
        }
        System.out.println("");
    }
    
    public static String toBitString(int[] bits) {
        StringBuilder sb = new StringBuilder();
        for (int i = bits.length - 1; i >= 0; i--) {
            sb.append(bits[i]);
        }
        return sb.toString();
    }

    public static String toBitString(double[] bits) {
        StringBuilder sb = new StringBuilder();
        for (int i = bits.length - 1; i >= 0; i--) {
            sb.append(bits[i]);
        }
        return sb.toString();
    }
}
