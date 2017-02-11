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

public class MoveShape {

    public shape changeShape(final shape temp, double x1, double y1, double x3,
            double y3) {
    	   if (UserInterface.loaded&&UserInterface.loadClass.isSquare(temp)) {
              Rectangle temp2 = (Rectangle) temp;
              double deltaX, deltaY;
              deltaX = (x3 - x1);
              deltaY = (y3 - y1);
              return UserInterface.loadClass.getObject(temp2.getX() + deltaX, temp2
                      .getY() + deltaY, temp2.getWidth(), temp2.getFillline(),
                      temp2.getOutline(),temp2.getstrokeThickness());
          } 
    	  else if (temp instanceof Line) {
            Line temp2 = (Line) temp;
            double deltaX, deltaY;
            deltaX = (x3 - x1);
            deltaY = (y3 - y1);
            return new Line(temp2.getX() + deltaX,
                    temp2.getY() + deltaY, temp2.getEndpointX() + deltaX,
                    temp2.getEndpointY() + deltaY, temp2.getFillline(),
                    temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Circle) {
            Circle temp2 = (Circle) temp;
            double deltaX, deltaY;
            deltaX = (x3 - x1);
            deltaY = (y3 - y1);
            return new Circle(temp2.getX() + deltaX, temp2.getY()
                    + deltaY, temp2.getMajoraxis(), temp2.getFillline(),
                    temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Ellipse) {
            Ellipse temp2 = (Ellipse) temp;
            double deltaX, deltaY;
            deltaX = (x3 - x1);
            deltaY = (y3 - y1);
            return new Ellipse(temp2.getX() + deltaX, temp2.getY()
                    + deltaY, temp2.getMinoraxis(), temp2.getMajoraxis(),
                    temp2.getFillline(), temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Rectangle) {
            Rectangle temp2 = (Rectangle) temp;
            double deltaX, deltaY;
            deltaX = (x3 - x1);
            deltaY = (y3 - y1);
            return new Rectangle(temp2.getX() + deltaX, temp2.getY()
                    + deltaY, temp2.getWidth(), temp2.getHeight(),
                    temp2.getFillline(), temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Triangle) {
            Triangle temp2 = (Triangle) temp;
            double deltaX, deltaY;
            deltaX = (x3 - x1);
            deltaY = (y3 - y1);
            int[] tempx = temp2.getXpts(), tempy = temp2.getYpts();
            ArrayList<Point> nxtState = new ArrayList<Point>();
            for (int i = 0; i < 3; i++) {
                Point cc = new Point();
                cc.setLocation(tempx[i] + deltaX, tempy[i] + deltaY);
                nxtState.add(cc);
            }
            return new Triangle(nxtState, temp2.getFillline(),
                    temp2.getOutline(),temp2.getstrokeThickness());
        }
      
        return new Line(0, 0, 0, 0, Color.WHITE, Color.WHITE,0);
    }

}
