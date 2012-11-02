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
    public ArrayList<IQGate> gates;
    
    public Point.Double origin = new Point.Double(0, 0);
    public double scale = 1;
    public double wireSpace = 20 * scale;
    public double gateSpace = wireSpace;
    public double excessWire = 2 * gateSpace;
    public double gateSize = 0.75 * wireSpace;
    public static final Color COLOR_SELECTED = new Color(0xD0, 0xFF, 0xFF);
    public static final Color COLOR_UNSELECTED = Color.cyan;
    public Color color = COLOR_UNSELECTED;
    
    public QCircuit(int bits) {
        this.bits = bits;
        this.gates = new ArrayList<IQGate>();
        
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
}
