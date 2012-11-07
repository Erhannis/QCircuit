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
public class MatrixGate implements IQGate {
    public int bits;
    public ComplexMatrix matrix;
    public static final Color COLOR_SELECTED = Color.orange.brighter();
    public static final Color COLOR_UNSELECTED = Color.orange;
    public Color color = COLOR_UNSELECTED;
    
    /**
     * Input a semicolon separated list of 2^(2n) numbers, get the square matrix it represents - in rows, L-R, top to bottom.
     * Technically should take complex numbers, but...not doing that, yet.
     * Ooh, I know!  Check out the one taking two strings!
     * @param csv 
     */
    public MatrixGate(String csv) {
        String[] strings = csv.split(";");
        int count = strings.length;
        count = (int)Math.sqrt(strings.length);
        int bucket = count;
        int n = 0;
        while (bucket > 1) {
            bucket = bucket >> 1;
            n++;
        }
        // Check:
        if (((bucket << n) * (bucket << n)) != strings.length) {
            throw new IllegalArgumentException("List must be 2^(2n) elements long, semicolon separated!");
        }
        this.bits = n;
        this.matrix = new ComplexMatrix(count, count);
        int index = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                matrix.values[i][j] = new Complex(Double.valueOf(strings[index++]), 0);
            }
        }
    }

    /**
     * Input two comma separated lists of 2^(n+1) numbers each, get the complex square matrix they represent - in rows, L-R, top to bottom.
     * @param csv 
     */
    public MatrixGate(String reals, String imags) {
        String[] rstrings = reals.split(";");
        String[] istrings = imags.split(";");
        int rcount = rstrings.length;
        int icount = istrings.length;
        if (rcount != icount) {
            throw new IllegalArgumentException("Differing number of elements in the two given number strings!");
        }
        rcount = (int)Math.sqrt(rstrings.length);
        icount = (int)Math.sqrt(istrings.length);
        int bucket = rcount;
        int n = 0;
        while (bucket > 1) {
            bucket = bucket >> 1;
            n++;
        }
        // Check:
        if (((bucket << n) * (bucket << n)) != rstrings.length) {
            throw new IllegalArgumentException("Lists must each be 2^(2n) elements long, semicolon separated!");
        }
        this.bits = n;
        this.matrix = new ComplexMatrix(rcount, rcount);
        int index = 0;
        for (int i = 0; i < rcount; i++) {
            for (int j = 0; j < rcount; j++) {
                matrix.values[i][j] = new Complex(Double.valueOf(rstrings[index]), Double.valueOf(istrings[index++]));
            }
        }
    }
    
    public MatrixGate(ComplexMatrix matrix) {
        if (matrix.cols != matrix.rows) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        this.matrix = matrix;
        int bucket = matrix.cols;
        int n = 0;
        while (bucket > 1) {
            bucket = bucket >> 1;
            n++;
        }
        // Check:
        if ((bucket << n) != matrix.cols) {
            throw new IllegalArgumentException("Matrix must be 2^n elements square!");
        }
        this.bits = n;
    }
    
    /**
     * The input better match up.
     * @param matrix
     * @param bits 
     */
    public MatrixGate(ComplexMatrix matrix, int bits) {
        this.matrix = matrix;
        this.bits = bits;
    }

    public void execute(QState state) {
        if (state.bits != this.bits) {
            throw new IllegalArgumentException("State must have the same number of bits as the matrix!");
        }
        QState newState = new QState(bits, false);
        int max = 1 << state.bits;
        for (int i = 0; i < max; i++) {
            Complex a = new Complex();
            for (int j = 0; j < max; j++) {
                a = a.plus(matrix.values[i][j].times(state.states[j]));
            }
            newState.states[i] = a;
        }
        state.states = newState.states;
    }

    public void reverseExecute(QState state) {
        this.execute(state);
    }

    /**
     * This really shouldn't be used often.  Way inefficient.
     * @param state
     * @param csv 
     */
    public static void execute(QState state, String csv) {
        String[] strings = csv.split(";");
        int count = strings.length;
        count = (int)Math.sqrt(strings.length);
        int bucket = count;
        int n = 0;
        while (bucket > 1) {
            bucket = bucket >> 1;
            n++;
        }
        // Check:
        if (((bucket << n) * (bucket << n)) != strings.length) {
            throw new IllegalArgumentException("List must be 2^(2n) elements long, semicolon separated!");
        }
        int bits = n;
        ComplexMatrix matrix = new ComplexMatrix(count, count);
        int index = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                matrix.values[i][j] = new Complex(Double.valueOf(strings[index++]), 0);
            }
        }
        if (state.bits != bits) {
            throw new IllegalArgumentException("State must have the same number of bits as the matrix!");
        }
        QState newState = new QState(bits, false);
        int max = 1 << state.bits;
        for (int i = 0; i < max; i++) {
            Complex a = new Complex();
            for (int j = 0; j < max; j++) {
                a = a.plus(matrix.values[i][j].times(state.states[j]));
            }
            newState.states[i] = a;
        }
        state.states = newState.states;
    }

    /**
     * This really shouldn't be used often.  Way inefficient.
     * @param state
     * @param csv 
     */
    public static void execute(QState state, String reals, String imags) {
        String[] rstrings = reals.split(";");
        String[] istrings = imags.split(";");
        int rcount = rstrings.length;
        int icount = istrings.length;
        if (rcount != icount) {
            throw new IllegalArgumentException("Differing number of elements in the two given number strings!");
        }
        rcount = (int)Math.sqrt(rstrings.length);
        icount = (int)Math.sqrt(istrings.length);
        int bucket = rcount;
        int n = 0;
        while (bucket > 1) {
            bucket = bucket >> 1;
            n++;
        }
        // Check:
        if (((bucket << n) * (bucket << n)) != rstrings.length) {
            throw new IllegalArgumentException("Lists must each be 2^(2n) elements long, semicolon separated!");
        }
        int bits = n;
        ComplexMatrix matrix = new ComplexMatrix(rcount, rcount);
        int index = 0;
        for (int i = 0; i < rcount; i++) {
            for (int j = 0; j < rcount; j++) {
                matrix.values[i][j] = new Complex(Double.valueOf(rstrings[index]), Double.valueOf(istrings[index++]));
            }
        }
        if (state.bits != bits) {
            throw new IllegalArgumentException("State must have the same number of bits as the matrix!");
        }
        QState newState = new QState(bits, false);
        int max = 1 << state.bits;
        for (int i = 0; i < max; i++) {
            Complex a = new Complex();
            for (int j = 0; j < max; j++) {
                a = a.plus(matrix.values[i][j].times(state.states[j]));
            }
            newState.states[i] = a;
        }
        state.states = newState.states;
    }
    
    public static void execute(QState state, ComplexMatrix matrix) {
        if (matrix.cols != matrix.rows) {
            throw new IllegalArgumentException("Matrix must be square!");
        }
        int bucket = matrix.cols;
        int n = 0;
        while (bucket > 1) {
            bucket = bucket >> 1;
            n++;
        }
        // Check:
        if ((bucket << n) != matrix.cols) {
            throw new IllegalArgumentException("Matrix must be 2^n elements square!");
        }
        int bits = n;
        if (state.bits != bits) {
            throw new IllegalArgumentException("State must have the same number of bits as the matrix!");
        }
        QState newState = new QState(bits, false);
        int max = 1 << state.bits;
        for (int i = 0; i < max; i++) {
            Complex a = new Complex();
            for (int j = 0; j < max; j++) {
                a = a.plus(matrix.values[i][j].times(state.states[j]));
            }
            newState.states[i] = a;
        }
        state.states = newState.states;
    }
    
    public static void reverseExecute(QState state, String csv) {
        execute(state, csv);
    }
    
    public static void reverseExecute(QState state, String reals, String imags) {
        execute(state, reals, imags);
    }

    public static void reverseExecute(QState state, ComplexMatrix matrix) {
        execute(state, matrix);
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
        return this.matrix;
    }

    /**
     * This assumes that this matrix is unitary.  Very important.
     * @return 
     */
    public ComplexMatrix toInverseMatrix() {
        return this.toMatrix().complexTranspose();
    }

    public IQGate toInverseGate() {
        return new MatrixGate(this.matrix.complexTranspose());
    }

    /**
     * This passes the original matrix.
     * @return 
     */
    public IQGate copy() {
        return new MatrixGate(this.matrix);
    }
}