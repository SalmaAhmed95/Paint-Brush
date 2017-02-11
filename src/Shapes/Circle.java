/**
 * 
 */
package Shapes;

import java.awt.Color;

public class Circle extends Ellipse {
    // constructor
    public Circle(double newx, double newy, double newradius,Color fill,Color out,int newStroke) {
        super(newx, newy, newradius, newradius,fill,out,newStroke);
    }
    
    public void SetRadius(double newRadius) {
        this.setMajoraxis(newRadius);
        this.setMinoraxis(newRadius);
    }
}
