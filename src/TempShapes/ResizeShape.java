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

public class ResizeShape {

    public shape Resize(final shape temp, double x1, double y1, double x3,
            double y3) {
    	 if (UserInterface.loaded&&UserInterface.loadClass.isSquare(temp)) {
             Rectangle temp2 = (Rectangle) temp;
             double deltaX;
             deltaX = (x3 - x1);
             return UserInterface.loadClass.getObject(temp2.getX(), temp2.getY(),
                     temp2.getWidth() + deltaX, temp2.getFillline(),
                     temp2.getOutline(),temp2.getstrokeThickness());
         }
    	 else if (temp instanceof Line) {
            Line temp2 = (Line) temp;
            double deltaX, deltaY;
            deltaX = (x3 - x1);
            deltaY = (y3 - y1);
            return new Line(temp2.getX(), temp2.getY(),
                    temp2.getEndpointX() + deltaX, temp2.getEndpointY()
                            + deltaY, temp2.getFillline(), temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Circle) {
            Circle temp2 = (Circle) temp;
            double deltaX, x, y, r;
            deltaX = (x3 - x1);
            r = temp2.getMinoraxis() + deltaX;
            x = temp2.getX() + (temp2.getMinoraxis() / 2) - (r / 2);
            y = temp2.getY() + (temp2.getMinoraxis() / 2) - (r / 2);
            return new Circle(x, y, r, temp2.getFillline(), temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Ellipse) {
            Ellipse temp2 = (Ellipse) temp;
            double deltaX, deltaY, x, y, r1, r2;
            deltaX = (x3 - x1);
            deltaY = (y3 - y1);
            r1 = temp2.getMinoraxis() + deltaX;
            r2 = temp2.getMajoraxis() + deltaY;
            x = temp2.getX() + (temp2.getMinoraxis() / 2) - (r1 / 2);
            y = temp2.getY() + (temp2.getMajoraxis() / 2) - (r2 / 2);
            return new Ellipse(x, y, r1, r2, temp2.getFillline(), temp2
                    .getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Rectangle) {
            Rectangle temp2 = (Rectangle) temp;
            double deltaX, deltaY;
            deltaX = (x3 - x1);
            deltaY = (y3 - y1);
            return new Rectangle(temp2.getX(), temp2.getY(),
                    temp2.getWidth() + deltaX, temp2.getHeight() + deltaY,
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
                if (i > 0)
                    cc.setLocation(tempx[i] + deltaX, tempy[i] + deltaY);
                else
                    cc.setLocation(tempx[i], tempy[i]);
                nxtState.add(cc);
            }
            return new Triangle(nxtState, temp2.getFillline(),
                    temp2.getOutline(),temp2.getstrokeThickness());
        }
        return new Line(0, 0, 0, 0, Color.WHITE, Color.WHITE,0);

    }
}
