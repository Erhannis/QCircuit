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
        int bucket = i;
        Arrays.sort(bits);
        for (int j = bits.length - 1; j >= 0; j--) {
            
        }
        return bucket;
    }
}
