/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

/**
 *
 * @author erhannis
 */
public class Complex {
    public double real = 0;
    public double imag = 0;
    
    public double norm() {
        return Math.sqrt(this.normsqr());
    }
    
    public double normsqr() {
        return ((real * real) + (imag * imag));
    }
}
