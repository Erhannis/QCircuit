/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author erhannis
 */
public class QUtils {
    public static int makeStateIndex(int i, int[] bits, HashMap<Integer, Integer> bitvals) {
//        int bucket = i;
//        Arrays.sort(bits);
//        bucket = ((bucket >> bits[bits.length - 1]) << 1) + bitvals.get(bits[bits.length - 1]);
//        bucket = bucket << bits[bits.length - 1]
//        for (int j = bits.length - 2; j >= 0; j--) {
//            bucket = (bucket << 1) + bitvals.get(bits[j]);
//        }
//        return bucket;
        return -1;
    }
    
    public static double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }
    
    public static double distSqr(double x1, double y1, double x2, double y2) {
        return (((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }
}
