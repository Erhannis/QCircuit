/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

/**
 *
 * @author erhannis
 */
public class Matrix<T> {
    public int rows = 0;
    public int cols = 0;
    public T[][] values;
    public ObjectGenerator<T> generator;
    
    public Matrix(int rows, int cols, ObjectGenerator<T> generator) {
        this.rows = rows;
        this.cols = cols;
        this.generator = generator;
        values = (T[][])new Object[rows][cols];
    }
    
    public void init() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                values[i][j] = generator.generate();
            }
        }
    }
}
