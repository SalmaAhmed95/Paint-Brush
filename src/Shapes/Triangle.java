/**
 * 
 */
package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;

import Files.JsonWriter;
import Files.XmlWriter;

public class Triangle extends shape {

    private int[] xPts, yPts;
    private Color out, fill;
    private int stroke;
    private boolean dotted;

    public Triangle(ArrayList<Point> a, Color newfill, Color newout,int newStroke) {
        super(0, 0);
        xPts = new int[3];
        yPts = new int[3];
        for (int i = 0; i < a.size(); i++) {
            xPts[i] = a.get(i).x;
            yPts[i] = a.get(i).y;
        }
        fillColor(newfill);
        colorOutline(newout);
        strokeThickness(newStroke);
        this.dotted = false;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(this.getstrokeThickness()));
        g.setColor(fill);
        g.fillPolygon(xPts, yPts, 3);
        float dash[] = {10.0f};
        if (dotted)
            g.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        g.setColor(out);
        g.drawPolygon(xPts, yPts, 3);

    }

    @Override
    public void colorOutline(Color c) {
        out = c;
    }

    public Color getOutline() {
        return out;

    }

    @Override
    public void fillColor(Color c) {
        fill = c;
    }

    public Color getFillline() {
        return fill;

    }

    public int[] getXpts() {
        return xPts;
    }

    public int[] getYpts() {
        return yPts;
    }

    public void setXpts(int[] arr) {
        xPts = arr;
    }

    public void setYpts(int[] arr) {
        yPts = arr;
    }

    public void setDot(boolean x) {
        this.dotted = x;
    }

    private boolean cn_PnPoly(Point P) {
        int cn = 0;
        for (int i = 0; i < 3; i++) {
            if (((yPts[i] <= P.getY()) && (yPts[(i + 1) % 3] > P.getY()))
                    || ((yPts[i] > P.getY()) && (yPts[(i + 1) % 3] <= P
                            .getY()))) {
                double vt = (double) (P.getY() - yPts[i]) / (yPts[(i + 1) % 3]
                        - yPts[i]);
                if (P.x < xPts[i] + vt * (xPts[(i + 1) % 3] - xPts[i]))
                    ++cn;
            }
        }
        return ((cn & 1) == 1);
    }

    @Override
    public boolean pointInShape(double x, double y) {
        Point temp = new Point();
        temp.setLocation(x, y);
        return cn_PnPoly(temp);
    }
	public Element createXml(int i) {

		Element shape = XmlWriter.doc.createElement("Shape");
		Element type = XmlWriter.doc.createElement("ShapeType");
		type.appendChild(XmlWriter.doc.createTextNode("Triangle"));

		Element id=XmlWriter.doc.createElement("id");
		Element x1 = XmlWriter.doc.createElement("x1");
		Element y1 = XmlWriter.doc.createElement("y1");
		Element x2 = XmlWriter.doc.createElement("x2");
		Element y2 = XmlWriter.doc.createElement("y2");
		Element x3 = XmlWriter.doc.createElement("x3");
		Element y3 = XmlWriter.doc.createElement("y3");
		
		id.appendChild(XmlWriter.doc.createTextNode(Integer.toString(i)));
		x1.appendChild(XmlWriter.doc.createTextNode(Integer
				.toString(getXpts()[0])));
		y1.appendChild(XmlWriter.doc.createTextNode(Integer
				.toString(getYpts()[0])));
		x2.appendChild(XmlWriter.doc.createTextNode(Integer
				.toString(getXpts()[1])));
		y2.appendChild(XmlWriter.doc.createTextNode(Integer
				.toString(getYpts()[1])));
		x3.appendChild(XmlWriter.doc.createTextNode(Integer
				.toString(getXpts()[2])));
		y3.appendChild(XmlWriter.doc.createTextNode(Integer
				.toString(getYpts()[2])));
		Element OutColor = XmlWriter.doc.createElement("OutlineColor");
		OutColor.appendChild(XmlWriter.doc.createTextNode(String
				.valueOf(getOutline())));
		Element FillColor = XmlWriter.doc.createElement("FillColor");
		OutColor.appendChild(XmlWriter.doc.createTextNode(String
				.valueOf(getFillline())));
		Element Stroke = XmlWriter.doc.createElement("Stroke");
		Stroke.appendChild(XmlWriter.doc.createTextNode(String
				.valueOf(getstrokeThickness())));
		shape.appendChild(Stroke);
		shape.appendChild(FillColor);
		shape.appendChild(OutColor);
		shape.appendChild(type);
		shape.appendChild(id);
		shape.appendChild(x1);
		shape.appendChild(y1);
		shape.appendChild(x2);
		shape.appendChild(y2);
		shape.appendChild(x3);
		shape.appendChild(y3);

		return shape;

	}

	public void createJson(int i) {
		JSONObject shape = new JSONObject();
		shape.put("type", "Triangle");
		shape.put("id", Integer.toString(i));
		shape.put("x1", Integer.toString(getXpts()[0]));
		shape.put("y1", Integer.toString(getYpts()[0]));
		shape.put("x2", Integer.toString(getXpts()[1]));
		shape.put("y2", Integer.toString(getYpts()[1]));
		shape.put("x3", Integer.toString(getXpts()[2]));
		shape.put("y3", Integer.toString(getYpts()[2]));
		shape.put("OutlineColor", String.valueOf(getOutline()));
		shape.put("FillColor", String.valueOf(getFillline()));
		shape.put("Stroke", Integer.toString(getstrokeThickness()));
		JsonWriter.shapes.add(shape);
	}

	@Override
	public void strokeThickness(int strokeVal) {
		this.stroke=strokeVal;
		
	}
	public int getstrokeThickness() {
		return this.stroke;
		
	}
}
