/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

/**
 *
 * @author erhannis
 */
public interface IQGate {
    public void execute(QState state);
    public void reverseExecute(QState state);
}
