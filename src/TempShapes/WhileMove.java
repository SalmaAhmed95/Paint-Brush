package TempShapes;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import DrawTools.UserInterface;
import Shapes.Ellipse;
import Shapes.Line;
import Shapes.Rectangle;
import Shapes.Triangle;
import Shapes.shape;

public class WhileMove {

    public shape whileMove(final int selected, final double x1, final double y1,
            final double x2, final double y2, final Color fillc,
            final Color outc,final int stroke, final boolean triangle, final boolean mouseMove,
            final ArrayList<Point> pts, final shape temp) {

    	 if (UserInterface.loaded&&UserInterface.loadClass.isSquare(temp)) {
             Rectangle temp2 = (Rectangle) temp;
             double deltaX, deltaY;
             deltaX = (x2 - x1);
             deltaY = (y2 - y1);
             return UserInterface.loadClass.getObject(temp2.getX() + deltaX, temp2
                     .getY() + deltaY, temp2.getWidth(),
                     temp2.getFillline(), temp2.getOutline(),temp2.getstrokeThickness());
         }
    	 else if (temp instanceof Line) {
            Line temp2 = (Line) temp;
            double deltaX, deltaY;
            deltaX = (x2 - x1);
            deltaY = (y2 - y1);
            return new Line(temp2.getX() + deltaX,
                    temp2.getY() + deltaY, temp2.getEndpointX() + deltaX,
                    temp2.getEndpointY() + deltaY, temp2.getFillline(),
                    temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Ellipse) {
            Ellipse temp2 = (Ellipse) temp;
            double deltaX, deltaY;
            deltaX = (x2 - x1);
            deltaY = (y2 - y1);
            return new Ellipse(temp2.getX() + deltaX, temp2.getY()
                    + deltaY, temp2.getMinoraxis(), temp2.getMajoraxis(),
                    temp2.getFillline(), temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Rectangle) {
            Rectangle temp2 = (Rectangle) temp;
            double deltaX, deltaY;
            deltaX = (x2 - x1);
            deltaY = (y2 - y1);
            return new Rectangle(temp2.getX() + deltaX, temp2.getY()
                    + deltaY, temp2.getWidth(), temp2.getHeight(),
                    temp2.getFillline(), temp2.getOutline(),temp2.getstrokeThickness());
        } else if (temp instanceof Triangle) {
            Triangle temp2 = (Triangle) temp;
            double deltaX, deltaY;
            deltaX = (x2 - x1);
            deltaY = (y2 - y1);
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
        return new Line(0, 0, 0, 0, fillc, outc,0);
    }

}
