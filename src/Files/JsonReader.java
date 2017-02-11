/**
 * 
 */
package Files;

import java.awt.Color;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DrawTools.UserInterface;
import Shapes.Ellipse;
import Shapes.Line;
import Shapes.Rectangle;
import Shapes.Triangle;
import Shapes.shape;

public class JsonReader {

    public ArrayList<shape> readJson(String path) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jobj = (JSONObject) obj;
            JSONArray jarray = (JSONArray) jobj.get("Shapes");

            ArrayList<shape> data = new ArrayList<shape>(jarray.size());
            for (int i = 0; i < jarray.size(); i++) {

                JSONObject currentShape = (JSONObject) jarray.get(i);

                String type = (String) currentShape.get("type");
                String id = (String) currentShape.get("id");
                int j = Integer.parseInt(id);

                if (type.equals("Ellipse")) {
                    String attr = (String) currentShape.get("xStart");
                    double xStart = Double.parseDouble(attr);
                    attr = (String) currentShape.get("yStart");
                    double yStart = Double.parseDouble(attr);

                    attr = (String) currentShape.get("MinorAxis");
                    double MinorAxis = Double.parseDouble(attr);

                    attr = (String) currentShape.get("MajorAxis");
                    double MajorAxis = Double.parseDouble(attr);
                    attr = (String) currentShape.get("FillColor");
                    Scanner sc1 = new Scanner(attr);
                    sc1.useDelimiter("\\D+");
                    Color out = new Color(sc1.nextInt(), sc1.nextInt(),
                            sc1.nextInt());
                    sc1.close();
                    attr = (String) currentShape.get("OutlineColor");
                    Scanner sc = new Scanner(attr);
                    sc.useDelimiter("\\D+");
                    Color fill = new Color(sc.nextInt(), sc.nextInt(),
                            sc.nextInt());
                    sc.close();
                    attr = (String) currentShape.get("Stroke");
                    int stroke = Integer.parseInt(attr);
                    shape loaded = new Ellipse(xStart, yStart, MinorAxis,
                            MajorAxis, out, fill, stroke);
                    data.add(j, loaded);

                } else if (type.equals("Line")) {
                    String attr = (String) currentShape.get("xStart");
                    double xStart = Double.parseDouble(attr);
                    attr = (String) currentShape.get("yStart");
                    double yStart = Double.parseDouble(attr);

                    attr = (String) currentShape.get("xEnd");
                    double xEnd = Double.parseDouble(attr);

                    attr = (String) currentShape.get("yEnd");
                    double yEnd = Double.parseDouble(attr);
                    attr = (String) currentShape.get("FillColor");
                    Scanner sc1 = new Scanner(attr);
                    sc1.useDelimiter("\\D+");
                    Color out = new Color(sc1.nextInt(), sc1.nextInt(),
                            sc1.nextInt());
                    sc1.close();
                    attr = (String) currentShape.get("OutlineColor");
                    Scanner sc = new Scanner(attr);
                    sc.useDelimiter("\\D+");
                    Color fill = new Color(sc.nextInt(), sc.nextInt(),
                            sc.nextInt());
                    sc.close();
                    attr = (String) currentShape.get("Stroke");
                    int stroke = Integer.parseInt(attr);
                    shape loaded = new Line(xStart, yStart, xEnd,
                            yEnd, out, fill, stroke);
                    data.add(j, loaded);

                } else if (type.equals("Rectangle")) {
                    String attr = (String) currentShape.get("xStart");
                    double xStart = Double.parseDouble(attr);
                    attr = (String) currentShape.get("yStart");
                    double yStart = Double.parseDouble(attr);

                    attr = (String) currentShape.get("Width");
                    double width = Double.parseDouble(attr);

                    attr = (String) currentShape.get("Height");
                    double height = Double.parseDouble(attr);
                    if (width == height && UserInterface.loaded == false) {
                        throw new RuntimeException();
                    }
                    attr = (String) currentShape.get("FillColor");
                    Scanner sc1 = new Scanner(attr);
                    sc1.useDelimiter("\\D+");
                    Color out = new Color(sc1.nextInt(), sc1.nextInt(),
                            sc1.nextInt());
                    sc1.close();
                    attr = (String) currentShape.get("OutlineColor");
                    Scanner sc = new Scanner(attr);
                    sc.useDelimiter("\\D+");
                    Color fill = new Color(sc.nextInt(), sc.nextInt(),
                            sc.nextInt());
                    sc.close();
                    attr = (String) currentShape.get("Stroke");
                    int stroke = Integer.parseInt(attr);
                    shape loaded = new Rectangle(xStart, yStart, width,
                            height, out, fill, stroke);
                    data.add(j, loaded);

                }

                else if (type.equals("Triangle")) {
                    String attr = (String) currentShape.get("x1");
                    int x1 = Integer.parseInt(attr);
                    attr = (String) currentShape.get("y1");
                    int y1 = Integer.parseInt(attr);

                    attr = (String) currentShape.get("x2");
                    int x2 = Integer.parseInt(attr);
                    attr = (String) currentShape.get("y2");
                    int y2 = Integer.parseInt(attr);

                    attr = (String) currentShape.get("x3");
                    int x3 = Integer.parseInt(attr);
                    attr = (String) currentShape.get("y3");
                    int y3 = Integer.parseInt(attr);
                    attr = (String) currentShape.get("FillColor");
                    Scanner sc1 = new Scanner(attr);
                    sc1.useDelimiter("\\D+");
                    Color out = new Color(sc1.nextInt(), sc1.nextInt(),
                            sc1.nextInt());
                    sc1.close();
                    attr = (String) currentShape.get("OutlineColor");
                    Scanner sc = new Scanner(attr);
                    sc.useDelimiter("\\D+");
                    Color fill = new Color(sc.nextInt(), sc.nextInt(),
                            sc.nextInt());
                    sc.close();
                    attr = (String) currentShape.get("Stroke");
                    int stroke = Integer.parseInt(attr);
                    ArrayList<Point> a = new ArrayList<Point>(3);
                    Point point1 = new Point(x1, y1);
                    a.add(point1);
                    Point point2 = new Point(x2, y2);
                    a.add(point2);
                    Point point3 = new Point(x3, y3);
                    a.add(point3);
                    shape loaded = new Triangle(a, out, fill, stroke);
                    data.add(j, loaded);

                }
            }

            return data;

        } catch (ParseException e) {
            return null;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

    }
}
