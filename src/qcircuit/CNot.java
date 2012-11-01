/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

/**
 * CNot.  The first bit is the one to be modified, the others are the controls.
 * If there is only one bit, CNot becomes Not.
 * @author erhannis
 */
public class CNot implements IQGate {
    public void execute(QState state, int[] bits) {
        if (bits.length == 0) {
            return;
        } else if (bits.length == 1) {
            // Augh, have to swap 2^-bitlength of all the state units.
            int bit = bits[0];
            int max = 1 << (state.bits - bits.length);
            for (int i = 0; i < max; i++) {
                
            }
        } else {
            int max = 1 << (state.bits - bits.length);
            for (int i = 0; i < max; i++) {
                
            }
        }
    }

    public void reverseExecute(QState state, int[] bits) {
        this.execute(state, bits);
    }
}
