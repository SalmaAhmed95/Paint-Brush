/**
 * 
 */
package Files;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import DrawTools.UserInterface;
import Shapes.Ellipse;
import Shapes.Line;
import Shapes.Rectangle;
import Shapes.Triangle;
import Shapes.shape;

public class XmlReader {
    public static DocumentBuilderFactory docFactory;
    public static DocumentBuilder docBuilder;
    public static Document doc;
    public static int square = 0;

    public ArrayList<shape> readXml(String filePath) {

        File loadedFile = new File(filePath);
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(loadedFile);
            doc.getDocumentElement().normalize();
            NodeList shapes = doc.getElementsByTagName("Shape");
            ArrayList<shape> data = new ArrayList<shape>(shapes.getLength());
            for (int i = 0; i < shapes.getLength(); i++) {

                Node currentNode = shapes.item(i);
                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element currentElement = (Element) currentNode;
                    String type = currentElement
                            .getElementsByTagName("ShapeType").item(0)
                            .getTextContent();
                    String id = currentElement.getElementsByTagName("id")
                            .item(0).getTextContent();
                    int j = Integer.parseInt(id);
                    if (type.equals("Ellipse")) {
                        String attr = currentElement
                                .getElementsByTagName("xStart").item(0)
                                .getTextContent();
                        double xStart = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("yStart")
                                .item(0).getTextContent();
                        double yStart = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("minorAxis")
                                .item(0).getTextContent();
                        double minoraxis = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("majorAxis")
                                .item(0).getTextContent();
                        double majoraxis = Double.parseDouble(attr);
                        attr = currentElement
                                .getElementsByTagName("OutlineColor").item(0)
                                .getTextContent();
                        Scanner sc = new Scanner(attr);
                        sc.useDelimiter("\\D+");
                        Color fill = new Color(sc.nextInt(), sc.nextInt(),
                                sc.nextInt());
                        Color out = new Color(sc.nextInt(), sc.nextInt(),
                                sc.nextInt());
                        sc.close();
                        attr = currentElement.getElementsByTagName("Stroke")
                                .item(0).getTextContent();
                        int stroke = Integer.parseInt(attr);
                        shape loaded = new Ellipse(xStart, yStart, minoraxis,
                                majoraxis, out, fill, stroke);
                        data.add(j, loaded);

                    } else if (type.equals("Line")) {
                        String attr = currentElement
                                .getElementsByTagName("xStart").item(0)
                                .getTextContent();
                        double xStart = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("yStart")
                                .item(0).getTextContent();
                        double yStart = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("EndX")
                                .item(0).getTextContent();
                        double xEnd = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("EndY")
                                .item(0).getTextContent();
                        double yEnd = Double.parseDouble(attr);
                        attr = currentElement
                                .getElementsByTagName("OutlineColor").item(0)
                                .getTextContent();
                        Scanner sc = new Scanner(attr);
                        sc.useDelimiter("\\D+");
                        Color fill = new Color(sc.nextInt(), sc.nextInt(),
                                sc.nextInt());
                        Color out = new Color(sc.nextInt(), sc.nextInt(),
                                sc.nextInt());
                        sc.close();
                        attr = currentElement.getElementsByTagName("Stroke")
                                .item(0).getTextContent();
                        int stroke = Integer.parseInt(attr);
                        shape loaded = new Line(xStart, yStart, xEnd, yEnd,
                                out, fill, stroke);
                        data.add(j, loaded);

                    } else if (type.equals("Rectangle")) {
                        String attr = currentElement
                                .getElementsByTagName("xStart").item(0)
                                .getTextContent();
                        double xStart = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("yStart")
                                .item(0).getTextContent();
                        double yStart = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("Width")
                                .item(0).getTextContent();
                        double width = Double.parseDouble(attr);
                        attr = currentElement.getElementsByTagName("Height")
                                .item(0).getTextContent();
                        double height = Double.parseDouble(attr);
                        if (width == height && UserInterface.loaded == false) {
                            square = 1;
                        }
                        attr = currentElement
                                .getElementsByTagName("OutlineColor").item(0)
                                .getTextContent();
                        Scanner sc = new Scanner(attr);
                        sc.useDelimiter("\\D+");
                        Color fill = new Color(sc.nextInt(), sc.nextInt(),
                                sc.nextInt());
                        Color out = new Color(sc.nextInt(), sc.nextInt(),
                                sc.nextInt());
                        sc.close();
                        attr = currentElement.getElementsByTagName("Stroke")
                                .item(0).getTextContent();
                        int stroke = Integer.parseInt(attr);
                        shape loaded = new Rectangle(xStart, yStart, width,
                                height, out, fill, stroke);
                        if (square != 1) {
                            data.add(j, loaded);
                        }

                    } else if (type.equals("Triangle")) {
                        String attr = currentElement.getElementsByTagName("x1")
                                .item(0).getTextContent();
                        int x1 = Integer.parseInt(attr);
                        attr = currentElement.getElementsByTagName("y1")
                                .item(0).getTextContent();
                        int y1 = Integer.parseInt(attr);
                        attr = currentElement.getElementsByTagName("x2")
                                .item(0).getTextContent();
                        int x2 = Integer.parseInt(attr);
                        attr = currentElement.getElementsByTagName("y2")
                                .item(0).getTextContent();
                        int y2 = Integer.parseInt(attr);
                        attr = currentElement.getElementsByTagName("x3")
                                .item(0).getTextContent();
                        int x3 = Integer.parseInt(attr);
                        attr = currentElement.getElementsByTagName("y3")
                                .item(0).getTextContent();
                        int y3 = Integer.parseInt(attr);
                        attr = currentElement
                                .getElementsByTagName("OutlineColor").item(0)
                                .getTextContent();
                        Scanner sc = new Scanner(attr);
                        sc.useDelimiter("\\D+");
                        Color fill = new Color(sc.nextInt(), sc.nextInt(),
                                sc.nextInt());
                        Color out = new Color(sc.nextInt(), sc.nextInt(),
                                sc.nextInt());
                        sc.close();
                        ArrayList<Point> a = new ArrayList<Point>(3);
                        Point point1 = new Point(x1, y1);
                        a.add(point1);
                        Point point2 = new Point(x2, y2);
                        a.add(point2);
                        Point point3 = new Point(x3, y3);
                        a.add(point3);
                        attr = currentElement.getElementsByTagName("Stroke")
                                .item(0).getTextContent();
                        int stroke = Integer.parseInt(attr);
                        shape loaded = new Triangle(a, out, fill, stroke);
                        data.add(j, loaded);
                    }
                }
            }
            return data;

        } catch (Exception e) {
            return null;
        }

    }

}
