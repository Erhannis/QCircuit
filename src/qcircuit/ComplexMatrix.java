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
}
