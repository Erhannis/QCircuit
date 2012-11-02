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
}
