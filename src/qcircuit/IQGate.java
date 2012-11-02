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
public interface IQGate {
    public void execute(QState state);
    public void reverseExecute(QState state);
    public Color getColor();
    public void setColor(Color color);
    public void setSelected(boolean selected);
}
