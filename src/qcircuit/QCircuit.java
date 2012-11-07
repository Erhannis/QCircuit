/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qcircuit;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author erhannis
 */
public class QCircuit implements IQCircuit {
    public int bits = 0;
    public Color[] wireColorsSelected;
    public Color[] wireColorsUnselected;
    public ArrayList<IQGate> gates;
    
    public Point.Double origin = new Point.Double(0, 0);
    public double scale = 1;
    public double wireSpace = 20 * scale;
    public double gateSpace = wireSpace * 1.5;
    public double excessWire = 2 * gateSpace;
    public double gateSize = 0.75 * wireSpace;
    public static final Color COLOR_SELECTED = new Color(0xD0, 0xFF, 0xFF);
    public static final Color COLOR_UNSELECTED = Color.cyan;
    public Color color = COLOR_UNSELECTED;
    
    public void switchRunMode(boolean runMode) {
        if (runMode) {
            gateSpace = wireSpace * 1.5;
        } else {
            gateSpace = wireSpace;
        }
    }
    
    public QCircuit(int bits) {
        this.bits = bits;
        this.gates = new ArrayList<IQGate>();
        this.wireColorsSelected = new Color[bits];
        this.wireColorsUnselected = new Color[bits];
        for (int i = 0; i < bits; i++) {
            this.wireColorsSelected[i] = COLOR_SELECTED;
            this.wireColorsUnselected[i] = COLOR_UNSELECTED;
        }
        
        init();
    }

    public QCircuit(int bits, double x, double y) {
        this.bits = bits;
        this.gates = new ArrayList<IQGate>();
        this.origin.x = x;
        this.origin.y = y;
        
        init();
    }
    
    public void init() {
        
    }

    public ComplexMatrix toMatrix() {
        ComplexMatrix result;
        if (gates.size() > 0) {
            result = gates.get(0).toMatrix();
            for (int i = 1; i < this.gates.size(); i++) {
                result = this.gates.get(i).toMatrix().times(result);
            }
        } else {
            result = ComplexMatrix.identity(1 << bits);
        }
        return result;
    }

    /**
     * This assumes the circuit's composite gate matrices are unitary.
     * @return 
     */
    public ComplexMatrix toInverseMatrix() {
        return this.toMatrix().complexTranspose();
    }
}
