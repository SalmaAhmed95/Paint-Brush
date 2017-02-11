package Shapes;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;

import java.awt.Color;
import java.awt.Graphics2D;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 
 */

public abstract class shape {
    private double x;
    private double y;

    // constructor
    shape(double newX, double newY) {
        moveTo(newX, newY);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double newx) {
        x = newx;
    }

    public void setY(double newy) {
        y = newy;
    }

    // to move the shape to new x & y position
    public void moveTo(double newX, double newY) {
        setX(newX);
        setY(newY);
    }

    // draw the shape whatever it is
    public abstract void draw(Graphics2D g);

    public abstract void colorOutline(Color c);

    public abstract Color getOutline();

    public abstract void fillColor(Color c);
    
    public abstract Color getFillline();
    
    public abstract void strokeThickness(int strokeVal);

    public abstract boolean pointInShape(double x, double y);

    public abstract void setDot(boolean b);
    public abstract Element createXml(int i) ;
    public abstract void createJson(int i);

}
