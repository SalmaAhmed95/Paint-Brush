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

public class WhileDraw {

    public shape whileDraw(final int selected, final double x1, final double y1,
            final double x2, final double y2, final Color fillc,
            final Color outc, final int stroke,
            final boolean triangle, final boolean mouseMove,
            final ArrayList<Point> pts) {
        if (selected == 1) {
            return new Line(x1, y1, x2, y2, fillc, outc, stroke);
        } else if (selected == 2) {
            double f, s, r1, r2;
            f = 2 * x1 - x2;
            s = 2 * y1 - y2;
            r1 = Math.abs(x2 - x1) * 2;
            r2 = Math.abs(y2 - y1) * 2;
            if (x1 > x2)
                f -= r1;
            if (y1 > y2)
                s -= r2;
            return new Ellipse(f, s, r1, r2, fillc, outc, stroke);
        } else if (selected == 3) {
            double r = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            return new Circle(x1 - r, y1 - r, r * 2.0, fillc, outc, stroke);
        } else if (selected == 4) {
            double f, s;
            f = x1;
            if (x1 > x2)
                f -= Math.abs(x2 - x1);
            s = y1;
            if (y1 > y2)
                s -= Math.abs(y2 - y1);
            return new Rectangle(f, s, Math.abs(x2 - x1),
                    Math.abs(y2 - y1), fillc, outc, stroke);
        } else if (selected == 5) {
            double r = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
            r /= Math.sqrt(2);
            double f, s;
            f = x1;
            if (x1 > x2)
                f -= r;
            s = y1;
            if (y1 > y2)
                s -= r;
            return UserInterface.loadClass.getObject(f, s, r, fillc, outc,
                    stroke);
        } else if (selected == 6 && triangle) {
            return new Line(x1, y1, x2, y2, fillc, outc, stroke);
        } else if (selected == 6 && mouseMove) {
            Point p = new Point();
            p.setLocation(x2, y2);
            pts.add(p);
            shape x = new Triangle(pts, fillc, outc, stroke);
            pts.remove(2);
            return x;
        }
        return new Line(0, 0, 0, 0, fillc, outc, 0);
    }
}
