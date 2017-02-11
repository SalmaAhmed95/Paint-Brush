package TempShapes;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import DrawTools.UserInterface;
import Shapes.Circle;
import Shapes.Ellipse;
import Shapes.Line;
import Shapes.Rectangle;
import Shapes.Triangle;
import Shapes.shape;

public class ChangeColor {

	public shape Change(final shape temp, Color x, boolean type) {
		Color f, s;
		if (type) {
			f = x;
			s = temp.getOutline();
		} else {
			f = temp.getFillline();
			s = x;
		}
	   if (UserInterface.loaded&&UserInterface.loadClass.isSquare(temp)) {
			Rectangle temp2 = (Rectangle) temp;
			return UserInterface.loadClass.getObject(temp2.getX(), temp2.getY(),
					temp2.getWidth(), f, s, temp2.getstrokeThickness());
		}
		else if (temp instanceof Line) {
			Line temp2 = (Line) temp;
			return new Line(temp2.getX(), temp2.getY(), temp2.getEndpointX(),
					temp2.getEndpointY(), f, s, temp2.getstrokeThickness());
		} else if (temp instanceof Circle) {
			Circle temp2 = (Circle) temp;
			return new Circle(temp2.getX(), temp2.getY(), temp2.getMajoraxis(),
					f, s, temp2.getstrokeThickness());
		} else if (temp instanceof Ellipse) {
			Ellipse temp2 = (Ellipse) temp;
			return new Ellipse(temp2.getX(), temp2.getY(),
					temp2.getMinoraxis(), temp2.getMajoraxis(), f, s,
					temp2.getstrokeThickness());
		} else if (temp instanceof Rectangle) {
			Rectangle temp2 = (Rectangle) temp;
			return new Rectangle(temp2.getX(), temp2.getY(), temp2.getWidth(),
					temp2.getHeight(), f, s, temp2.getstrokeThickness());
		} else if (temp instanceof Triangle) {
			Triangle temp2 = (Triangle) temp;
			int[] tempx = temp2.getXpts(), tempy = temp2.getYpts();
			ArrayList<Point> nxtState = new ArrayList<Point>();
			for (int i = 0; i < 3; i++) {
				Point cc = new Point();
				cc.setLocation(tempx[i], tempy[i]);
				nxtState.add(cc);
			}
			return new Triangle(nxtState, f, s, temp2.getstrokeThickness());
		} 
		return new Line(0, 0, 0, 0, Color.WHITE, Color.WHITE, 0);
	}

}
