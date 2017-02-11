/**
 * 
 */
package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;

import Files.JsonWriter;
import Files.XmlWriter;

public class Rectangle extends shape {
	private double width;
	private double height;
	private Color out, fill;
	private int stroke;
	private boolean dotted;

	public Rectangle(double newX, double newY, double newWidth,
			double newHeight, Color newfill, Color newout,int newStroke) {
		super(newX, newY);
		setWidth(newWidth);
		setHeight(newHeight);
		colorOutline(newout);
		fillColor(newfill);
		strokeThickness(newStroke);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setWidth(double newwidth) {
		width = newwidth;
	}

	public void setHeight(double newheight) {
		height = newheight;
	}

	public void setDot(boolean x) {
		this.dotted = x;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(this.getstrokeThickness()));
		g.setColor(this.getFillline());
		g.fill(new Rectangle2D.Double(this.getX(), this.getY(), width, height));
		float dash[] = { 10.0f };
		if (dotted)
			g.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		g.setColor(this.getOutline());
		g.draw(new Rectangle2D.Double(this.getX(), this.getY(), width, height));

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

	@Override
	public boolean pointInShape(double x, double y) {
		double x1, x2, y1, y2;
		x1 = this.getX();
		y1 = this.getY();
		x2 = x1 + width;
		y2 = y1 + height;
		boolean v1, v2;
		v1 = (x >= x1 && x <= x2);
		v2 = (y >= y1 && y <= y2);
		return v1 && v2;
	}

	public Element createXml(int i) {

		Element shape = XmlWriter.doc.createElement("Shape");
		Element type = XmlWriter.doc.createElement("ShapeType");
		type.appendChild(XmlWriter.doc.createTextNode("Rectangle"));
		
		Element id=XmlWriter.doc.createElement("id");
		Element xStart = XmlWriter.doc.createElement("xStart");
		Element yStart = XmlWriter.doc.createElement("yStart");
		Element width = XmlWriter.doc.createElement("Width");
		Element height = XmlWriter.doc.createElement("Height");

		id.appendChild(XmlWriter.doc.createTextNode(Integer.toString(i)));
		xStart.appendChild(XmlWriter.doc.createTextNode(Double.toString(getX())));
		yStart.appendChild(XmlWriter.doc.createTextNode(Double.toString(getY())));
		width.appendChild(XmlWriter.doc.createTextNode(Double
				.toString(getWidth())));
		height.appendChild(XmlWriter.doc.createTextNode(Double
				.toString(getHeight())));

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
		shape.appendChild(xStart);
		shape.appendChild(yStart);
		shape.appendChild(width);
		shape.appendChild(height);

		return shape;

	}

	public void createJson(int i) {
		JSONObject shape = new JSONObject();
		shape.put("type", "Rectangle");
		shape.put("id", Integer.toString(i));
		shape.put("xStart", Double.toString(getX()));
		shape.put("yStart", Double.toString(getY()));
		shape.put("Width", Double.toString(getWidth()));
		shape.put("Height", Double.toString(getHeight()));
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
