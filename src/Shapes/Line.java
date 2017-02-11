/**
 * 
 */
package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;

import Files.JsonWriter;
import Files.XmlWriter;

public class Line extends shape {
	private double endpointX;
	private double endpointY;
	private Color out, fill;
	private int stroke;
	private boolean dotted;

	// constructor
	public Line(double newXstart, double newYstart, double newXend,
			double newYend, Color newfill, Color newout, int newStroke) {
		super(newXstart, newYstart);
		setEndpointX(newXend);
		setEndpointY(newYend);
		fillColor(newfill);
		colorOutline(newout);
		strokeThickness(newStroke);
	}

	public double getEndpointX() {
		return endpointX;
	}

	public double getEndpointY() {
		return endpointY;
	}

	public void setEndpointX(double newXend) {
		endpointX = newXend;
	}

	public void setEndpointY(double newYend) {
		endpointY = newYend;
	}

	public void setDot(boolean x) {
		this.dotted = x;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(this.getstrokeThickness()));
		float dash[] = { 10.0f };
		if (dotted)
			g.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		g.setColor(this.getOutline());
		g.draw(new Line2D.Double(this.getX(), this.getY(), endpointX, endpointY));

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
		if (this.getX() == endpointX)
			return (y >= Math.min(this.getY(), endpointY) && (y <= Math.max(
					this.getY(), endpointY)));
		if (this.getY() == endpointY)
			return (x >= Math.min(this.getX(), endpointX) && (x <= Math.max(
					this.getX(), endpointX)));
		double slope = (this.getY() - endpointY) / (this.getX() - endpointX);
		double val = (slope * x) - (slope * endpointX) + endpointY;
		return Math.abs(y - val) <= 8;
	}

	public Element createXml(int i) {

		Element shape = XmlWriter.doc.createElement("Shape");
		Element type = XmlWriter.doc.createElement("ShapeType");
		type.appendChild(XmlWriter.doc.createTextNode("Line"));
		Element id = XmlWriter.doc.createElement("id");
		Element xStart = XmlWriter.doc.createElement("xStart");
		Element yStart = XmlWriter.doc.createElement("yStart");
		Element xEnd = XmlWriter.doc.createElement("EndX");
		Element yEnd = XmlWriter.doc.createElement("EndY");

		id.appendChild(XmlWriter.doc.createTextNode(Integer.toString(i)));
		xStart.appendChild(XmlWriter.doc.createTextNode(Double.toString(getX())));
		yStart.appendChild(XmlWriter.doc.createTextNode(Double.toString(getY())));
		xEnd.appendChild(XmlWriter.doc.createTextNode(Double
				.toString(getEndpointX())));
		yEnd.appendChild(XmlWriter.doc.createTextNode(Double
				.toString(getEndpointY())));

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
		shape.appendChild(xEnd);
		shape.appendChild(yEnd);

		return shape;

	}

	public void createJson(int i) {
		JSONObject shape = new JSONObject();
		shape.put("type", "Line");
		shape.put("id", Integer.toString(i));
		shape.put("xStart", Double.toString(getX()));
		shape.put("yStart", Double.toString(getY()));
		shape.put("xEnd", Double.toString(getEndpointX()));
		shape.put("yEnd", Double.toString(getEndpointY()));
		shape.put("OutlineColor", String.valueOf(getOutline()));
		shape.put("FillColor", String.valueOf(getFillline()));
		shape.put("Stroke", Integer.toString(getstrokeThickness()));
		JsonWriter.shapes.add(shape);
	}

	public void strokeThickness(int strokeVal) {
		this.stroke = strokeVal;

	}

	public int getstrokeThickness() {
		return this.stroke;

	}

}
