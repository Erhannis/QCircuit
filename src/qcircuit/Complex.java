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
public class Complex {
    public double real = 0;
    public double imag = 0;
    
    public Complex() {
    }
    
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    
    public double norm() {
        return Math.sqrt(this.normsqr());
    }
    
    public double normsqr() {
        return ((real * real) + (imag * imag));
    }
    
    public Complex copy() {
        return new Complex(this.real, this.imag);
    }
    
    public Complex times(Complex b) {
        return new Complex((this.real * b.real) - (this.imag * b.imag), (this.imag * b.real) + (this.real * b.imag));
    }

    public Complex div(Complex b) {
        return new Complex((((this.real*b.real)+(this.imag*b.imag))/((b.real*b.real)+(b.imag*b.imag))),(((this.imag*b.real)-(this.real*b.imag))/((b.real*b.real)+(b.imag*b.imag))));
    }
    
    public Complex plus(Complex b) {
        return new Complex(this.real + b.real, this.imag + b.imag);
    }

    public Complex minus(Complex b) {
        return new Complex(this.real - b.real, this.imag - b.imag);
    }
    
    public Complex times(double b) {
        return new Complex(this.real * b, this.imag * b);
    }

    public Complex div(double b) {
        return new Complex(this.real / b, this.imag / b);
    }
    
    public Complex plus(double b) {
        return new Complex(this.real + b, this.imag);
    }

    public Complex minus(double b) {
        return new Complex(this.real - b, this.imag);
    }

    public Complex conjugate() {
        return new Complex(this.real, -(this.imag));
    }
    
    public static NumberFormat nf = NumberFormat.getInstance();
    static {
        nf.setMaximumFractionDigits(2);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nf.format(real));
        if (Math.copySign(1, imag) >= 0)
            sb.append("+");
        sb.append(nf.format(imag) + "i");
        return sb.toString();
    }

    public String toString(NumberFormat nf) {
        StringBuilder sb = new StringBuilder();
        sb.append(nf.format(real));
        if (Math.copySign(1, imag) >= 0)
            sb.append("+");
        sb.append(nf.format(imag) + "i");
        return sb.toString();
    }

    public String toStringWSigns(NumberFormat nf) {
        StringBuilder sb = new StringBuilder();
        if (Math.copySign(1, real) >= 0) {
            sb.append("+");
        }
        sb.append(nf.format(real));
        if (Math.copySign(1, imag) >= 0)
            sb.append("+");
        sb.append(nf.format(imag) + "i");
        return sb.toString();
    }

    public String toStringWPad(NumberFormat nf) {
        StringBuilder sb = new StringBuilder();
        if (Math.copySign(1, real) >= 0) {
            sb.append(" ");
        }
        sb.append(nf.format(real));
        if (Math.copySign(1, imag) >= 0)
            sb.append("+");
        sb.append(nf.format(imag) + "i");
        return sb.toString();
    }
}
