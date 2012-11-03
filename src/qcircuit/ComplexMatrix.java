/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

/**
 *
 * @author erhannis
 */
public class ComplexMatrix {
    public int rows = 0;
    public int cols = 0;
    public Complex[][] values;
    
    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        values = new Complex[rows][cols];
    }
    
    public void init() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                values[i][j] = new Complex();
            }
        }
    }
    
    public String toSquareExportString() {
        StringBuilder real = new StringBuilder();
        StringBuilder imag = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                real.append(Double.toString(values[i][j].real) + ";");
                imag.append(Double.toString(values[i][j].imag) + ";");                
            }
        }
        real.deleteCharAt(real.length() - 1);
        imag.deleteCharAt(imag.length() - 1);
        real.append(":" + imag);
        return real.toString();
    }
    
    public ComplexMatrix times(ComplexMatrix b) {
        if (b.cols != this.rows || b.rows != this.cols) {
            throw new IllegalArgumentException("Incompatible matrices!");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, b.cols);
        for (int i = 0; i < b.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                Complex value = new Complex();
                for (int k = 0; k < b.rows; k++) {
                    value = value.plus(b.values[k][i].times(this.values[j][k]));
                }
                result.values[i][j] = value;
            }
        }
        return result;
    }
    
    public static ComplexMatrix identity(int size) {
        ComplexMatrix result = new ComplexMatrix(size, size);
        result.init();
        for (int i = 0; i < size; i++) {
            result.values[i][i] = new Complex(1,0);
        }
        return result;
    }
    
    public ComplexMatrix complexTranspose() {
        ComplexMatrix result = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.values[j][i] = this.values[i][j].conjugate();
            }
        }
        return result;
    }
    
    public boolean checkIdentity(int binprecision, boolean allowRectangular) {
        if (!allowRectangular && rows != cols) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i != j) {
                    if ((((int) (values[i][j].real * (1 << binprecision))) != 0)
                            || (((int) (values[i][j].imag * (1 << binprecision))) != 0)) {
                        return false;
                    }
                } else {
                    if ((((int) (values[i][j].real * (1 << binprecision))) != 1)
                            || (((int) (values[i][j].imag * (1 << binprecision))) != 0)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkUnitary() {
        return this.complexTranspose().times(this).checkIdentity(20, true);
    }
}
