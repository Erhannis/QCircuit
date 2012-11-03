/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

/**
 *
 * @author erhannis
 */
public interface IQCircuit {
    public ComplexMatrix toMatrix();
    public ComplexMatrix toInverseMatrix();
}
