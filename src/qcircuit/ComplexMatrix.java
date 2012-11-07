/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

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
    
    /**
     * Returns a string formatted like<br/>
     * /0.0+1.0i 0.0+0.0i 0.0+0.0i\
     * |0.0+0.0i 1.0+0.0i 0.0+0.0i|
     * \0.0+1.0i 0.0+0.0i 0.0-0.0i/
     * 
     * Strictness, 0-5, controls how precisely the edges
     * line up. Higher values may make things look odd
     * (like 0001.00+0000.00i, etc.), and may require extra
     * processing. 3-5 ensure exact alignment; 5 shows + in front
     * of all positive numbers, and 3-5 have 1-3 (respectively) fraction digits visible.
     * 
     * @param strictness
     * @return 
     */
    public String toMatrixForm(int strictness) {
        StringBuilder result = new StringBuilder();
        NumberFormat nf = NumberFormat.getInstance();
        switch (strictness) {
            case 1:
                for (int y = 0; y < rows; y++) {
                    if (y == 0) {
                        result.append("/");
                    } else if (y == (rows - 1)) {
                        result.append("\\");
                    } else {
                        result.append("|");
                    }

                    for (int x = 0; x < cols; x++) {
                        result.append(values[y][x].toStringWPad(nf) + " ");
                    }
                    if (y == 0) {
                        result.append("\\\n");
                    } else if (y == (rows - 1)) {
                        result.append("/\n");
                    } else {
                        result.append("|\n");
                    }
                }
                break;
            case 2:
                nf.setMaximumFractionDigits(2);
                nf.setMinimumFractionDigits(2);
                nf.setMinimumIntegerDigits(4);
                
                for (int y = 0; y < rows; y++) {
                    if (y == 0) {
                        result.append("/");
                    } else if (y == (rows - 1)) {
                        result.append("\\");
                    } else {
                        result.append("|");
                    }

                    for (int x = 0; x < cols; x++) {
                        result.append(values[y][x].toStringWPad(nf) + " ");
                    }
                    if (y == 0) {
                        result.append("\\\n");
                    } else if (y == (rows - 1)) {
                        result.append("/\n");
                    } else {
                        result.append("|\n");
                    }
                }
                break;
            case 3:
                nf.setMaximumFractionDigits(1);
                nf.setMinimumFractionDigits(1);
                int maxDigits = 0;                
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        int bucket = (int)(Math.log10(Math.abs(values[i][j].real)) + 1);
                        if (bucket > maxDigits) {
                            maxDigits = bucket;
                        }
                        bucket = (int)(Math.log10(Math.abs(values[i][j].imag)) + 1);
                        if (bucket > maxDigits) {
                            maxDigits = bucket;
                        }
                    }
                }
                nf.setMinimumIntegerDigits(maxDigits);
                
                for (int y = 0; y < rows; y++) {
                    if (y == 0) {
                        result.append("/");
                    } else if (y == (rows - 1)) {
                        result.append("\\");
                    } else {
                        result.append("|");
                    }

                    for (int x = 0; x < cols; x++) {
                        result.append(values[y][x].toStringWPad(nf) + " ");
                    }
                    if (y == 0) {
                        result.append("\\\n");
                    } else if (y == (rows - 1)) {
                        result.append("/\n");
                    } else {
                        result.append("|\n");
                    }
                }
                break;
            case 4:
                nf.setMaximumFractionDigits(2);
                nf.setMinimumFractionDigits(2);
                maxDigits = 0;                
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        int bucket = (int)(Math.log10(Math.abs(values[i][j].real)) + 1);
                        if (bucket > maxDigits) {
                            maxDigits = bucket;
                        }
                        bucket = (int)(Math.log10(Math.abs(values[i][j].imag)) + 1);
                        if (bucket > maxDigits) {
                            maxDigits = bucket;
                        }
                    }
                }
                nf.setMinimumIntegerDigits(maxDigits);
                
                for (int y = 0; y < rows; y++) {
                    if (y == 0) {
                        result.append("/");
                    } else if (y == (rows - 1)) {
                        result.append("\\");
                    } else {
                        result.append("|");
                    }

                    for (int x = 0; x < cols; x++) {
                        result.append(values[y][x].toStringWPad(nf) + " ");
                    }
                    if (y == 0) {
                        result.append("\\\n");
                    } else if (y == (rows - 1)) {
                        result.append("/\n");
                    } else {
                        result.append("|\n");
                    }
                }
                break;
            case 5:
                nf.setMaximumFractionDigits(3);
                nf.setMinimumFractionDigits(3);
                maxDigits = 0;                
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        int bucket = (int)(Math.log10(Math.abs(values[i][j].real)) + 1);
                        if (bucket > maxDigits) {
                            maxDigits = bucket;
                        }
                        bucket = (int)(Math.log10(Math.abs(values[i][j].imag)) + 1);
                        if (bucket > maxDigits) {
                            maxDigits = bucket;
                        }
                    }
                }
                nf.setMinimumIntegerDigits(maxDigits);
                
                for (int y = 0; y < rows; y++) {
                    if (y == 0) {
                        result.append("/");
                    } else if (y == (rows - 1)) {
                        result.append("\\");
                    } else {
                        result.append("|");
                    }

                    for (int x = 0; x < cols; x++) {
                        result.append(values[y][x].toStringWSigns(nf) + " ");
                    }
                    if (y == 0) {
                        result.append("\\\n");
                    } else if (y == (rows - 1)) {
                        result.append("/\n");
                    } else {
                        result.append("|\n");
                    }
                }
                break;
            case 0:
            default:
                for (int y = 0; y < rows; y++) {
                    if (y == 0) {
                        result.append("/");
                    } else if (y == (rows - 1)) {
                        result.append("\\");
                    } else {
                        result.append("|");
                    }

                    for (int x = 0; x < cols; x++) {
                        result.append(values[y][x].toString(nf) + " ");
                    }
                    if (y == 0) {
                        result.append("\\\n");
                    } else if (y == (rows - 1)) {
                        result.append("/\n");
                    } else {
                        result.append("|\n");
                    }
                }
                break;
        }
        return result.toString();
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
