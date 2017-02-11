/**
 * 
 */
package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;

import Files.JsonWriter;
import Files.XmlWriter;

public class Ellipse extends shape {
	private double minorAxis;
	private double majorAxis;
	private boolean dotted;
	private Color out, fill;
	private int stroke;

	// constructor
	public Ellipse(double newX, double newY, double newMinoraxis,
			double newMajoraxis, Color newfill, Color newout,int newStroke) {
		super(newX, newY);
		setMinoraxis(newMinoraxis);
		setMajoraxis(newMajoraxis);
		fillColor(newfill);
		colorOutline(newout);
		strokeThickness(newStroke);
	}

	public double getMinoraxis() {
		return minorAxis;
	}

	public double getMajoraxis() {
		return majorAxis;
	}

	public void setMinoraxis(double newMinoraxis) {
		minorAxis = newMinoraxis;
	}

	public void setMajoraxis(double newMajoraxis) {
		majorAxis = newMajoraxis;
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
		g.fill(new Ellipse2D.Double(this.getX(), this.getY(), minorAxis,
				majorAxis));
		float dash[] = { 10.0f };
		if (dotted)
			g.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
		g.setColor(this.getOutline());
		g.draw(new Ellipse2D.Double(this.getX(), this.getY(), minorAxis,
				majorAxis));
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
		double tempx, tempy, r1, r2;
		r1 = this.getMinoraxis() / 2;
		r2 = this.getMajoraxis() / 2;
		tempx = r1 + this.getX();
		tempy = r2 + this.getY();
		double val1 = ((x - tempx) * (x - tempx)) / (r1 * r1);
		double val2 = ((y - tempy) * (y - tempy)) / (r2 * r2);
		return val1 + val2 <= 1;
	}

	public Element createXml(int i) {

		Element shape = XmlWriter.doc.createElement("Shape");
		Element type = XmlWriter.doc.createElement("ShapeType");
		type.appendChild(XmlWriter.doc.createTextNode("Ellipse"));

		Element id=XmlWriter.doc.createElement("id");
		Element xStart = XmlWriter.doc.createElement("xStart");
		Element yStart = XmlWriter.doc.createElement("yStart");
		Element minorAxis = XmlWriter.doc.createElement("minorAxis");
		Element majorAxis = XmlWriter.doc.createElement("majorAxis");

		id.appendChild(XmlWriter.doc.createTextNode(Integer.toString(i)));
		xStart.appendChild(XmlWriter.doc.createTextNode(Double.toString(getX())));
		yStart.appendChild(XmlWriter.doc.createTextNode(Double.toString(getY())));
		minorAxis.appendChild(XmlWriter.doc.createTextNode(Double.toString(getMinoraxis())));
		majorAxis.appendChild(XmlWriter.doc.createTextNode(Double.toString(getMajoraxis())));

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
	    shape.appendChild(minorAxis);
	    shape.appendChild(majorAxis);

		return shape;

	}
	public void createJson(int i){
		JSONObject shape = new JSONObject();
	     shape.put("type", "Ellipse");
	     shape.put("id", Integer.toString(i));
		shape.put("xStart", Double.toString(getX()));
		shape.put("yStart", Double.toString(getY()));
		shape.put("MinorAxis", Double.toString(getMinoraxis()));
		shape.put("MajorAxis", Double.toString(getMajoraxis()));
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
