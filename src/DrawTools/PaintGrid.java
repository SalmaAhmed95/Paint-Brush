package DrawTools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Shapes.Circle;
import Shapes.Ellipse;
import Shapes.Line;
import Shapes.Rectangle;
import Shapes.Triangle;
import Shapes.shape;
import TempShapes.ChangeColor;
import TempShapes.MoveShape;
import TempShapes.ResizeShape;
import TempShapes.WhileDraw;
import TempShapes.WhileMove;
import TempShapes.WhileResize;

public class PaintGrid extends JPanel implements GridInterface {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Graphics g;
    private double x1, x2, x3, y1, y2, y3;
    private int selected = 0, shapeAction = -1;
    private Stack<ArrayList<shape>> left, right;
    private ArrayList<shape> data;
    private ArrayList<Point> pts;
    private boolean mouseClck, moveMode, resizeMode, mouseMove, triangle;
    private Color outc, fillc;
    private int strokeVal;
    private BufferedImage curGrid;

    public PaintGrid() {
        data = new ArrayList<shape>();
        pts = new ArrayList<Point>();
        setLeft(new Stack<ArrayList<shape>>());
        setRight(new Stack<ArrayList<shape>>());

        mouseClck = false;
        mouseMove = false;
        moveMode = false;
        resizeMode = false;
        triangle = false;
        MyListener();
        repaint();
    }

    private void whileDraw(Graphics2D g2d) {
        WhileDraw get = new WhileDraw();
        shape x = get.whileDraw(selected, x1, y1, x2, y2, fillc, outc,
                strokeVal, triangle, mouseMove, pts);
        x.draw(g2d);
    }

    private void whileMove(Graphics2D g2d) {
        if (shapeAction == -1)
            throw new RuntimeException();
        shape temp = data.get(shapeAction);
        WhileMove get = new WhileMove();
        shape x = get.whileMove(selected, x1, y1, x2, y2, fillc, outc,
                strokeVal, triangle, mouseMove, pts, temp);
        x.draw(g2d);
    }

    private void whileResize(Graphics2D g2d) {
        if (shapeAction == -1)
            throw new RuntimeException();
        shape temp = data.get(shapeAction);
        WhileResize get = new WhileResize();
        shape x = get.whileResize(selected, x1, y1, x2, y2, fillc, outc,
                strokeVal, triangle, mouseMove, pts, temp);
        x.draw(g2d);
    }

    private void setShape() {
        if (selected == 1) {
            data.add(new Line(x1, y1, x3, y3, fillc, outc, strokeVal));
            selected = 0;
        } else if (selected == 2) {
            double f, s, r1, r2;
            f = 2 * x1 - x3;
            s = 2 * y1 - y3;
            r1 = Math.abs(x3 - x1) * 2;
            r2 = Math.abs(y3 - y1) * 2;
            if (x1 > x3)
                f -= r1;
            if (y1 > y3)
                s -= r2;
            data.add(new Ellipse(f, s, r1, r2, fillc, outc, strokeVal));
            selected = 0;
        } else if (selected == 3) {
            double r = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
            data.add(new Circle(x1 - r, y1 - r, r * 2.0, fillc, outc,
                    strokeVal));
            selected = 0;
        } else if (selected == 4) {
            double f, s;
            f = x1;
            if (x1 > x3)
                f -= Math.abs(x3 - x1);
            s = y1;
            if (y1 > y3)
                s -= Math.abs(y3 - y1);
            data.add(new Rectangle(f, s, Math.abs(x3 - x1), Math.abs(y3 - y1),
                    fillc, outc, strokeVal));
            selected = 0;
        } else if (selected == 5) {
            double r = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));
            r /= Math.sqrt(2);
            double f, s;
            f = x1;
            if (x1 > x3)
                f -= r;
            s = y1;
            if (y1 > y3)
                s -= r;
            data.add(UserInterface.loadClass.getObject(f, s, r, fillc, outc,
                    strokeVal));
            selected = 0;
        } else if (selected == 6 && pts.size() == 2) {
            Point temp = new Point();
            temp.setLocation(x3, y3);
            pts.add(temp);
            data.add(new Triangle(pts, fillc, outc, strokeVal));
            pts.clear();
            selected = 0;
            mouseMove = false;
        }

    }

    private void changeShape() {
        if (shapeAction == -1) {
            JOptionPane.showMessageDialog(null,
                    "Please Move The Shape Correctly");
            return;
        }
        shape temp = data.get(shapeAction);
        MoveShape get = new MoveShape();
        shape x = get.changeShape(temp, x1, y1, x3, y3);
        data.remove(shapeAction);
        data.add(shapeAction, x);
        shapeAction = -1;
    }

    private void changeShape2() {
        if (shapeAction == -1) {
            JOptionPane.showMessageDialog(null,
                    "Please Resize The Shape Correctly");
            return;
        }
        shape temp = data.get(shapeAction);
        ResizeShape get = new ResizeShape();
        shape x = get.Resize(temp, x1, y1, x3, y3);
        data.remove(shapeAction);
        data.add(shapeAction, x);
        shapeAction = -1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        curGrid = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D i2d = curGrid.createGraphics();
        if (shapeAction != -1) {
            data.get(shapeAction).setDot(true);
        }
        for (int i = 0; i < data.size(); i++) {
            if ((moveMode || resizeMode) && shapeAction == i)
                continue;
            data.get(i).draw(g2d);
            data.get(i).draw(i2d);
        }
        if (shapeAction != -1)
            data.get(shapeAction).setDot(false);
        if (mouseClck || mouseMove)
            whileDraw(g2d);
        if (moveMode && shapeAction != -1)
            whileMove(g2d);
        if (resizeMode && shapeAction != -1)
            whileResize(g2d);
    }

    void MyListener() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent arg0) {
                x1 = arg0.getX();
                y1 = arg0.getY();
                if (selected == 6 && mouseMove)
                    return;
                if (selected == 0) {
                    shapeAction = isClicked(x1, y1);
                    repaint();
                }
                mouseClck = true;
                triangle = true;
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                x3 = arg0.getX();
                y3 = arg0.getY();
                mouseClck = false;
                if (selected != 0) {
                    left.push(new ArrayList<shape>(data));
                    right.clear();
                    setShape();
                } else if (moveMode) {
                    left.push(new ArrayList<shape>(data));
                    right.clear();
                    changeShape();
                    moveMode = false;
                    shapeAction = -1;
                } else if (resizeMode) {
                    left.push(new ArrayList<shape>(data));
                    right.clear();
                    changeShape2();
                    resizeMode = false;
                    shapeAction = -1;
                }
                if (selected == 6) {
                    Point p1 = new Point();
                    p1.setLocation(x1, y1);
                    pts.add(p1);
                    Point p2 = new Point();
                    p2.setLocation(x3, y3);
                    pts.add(p2);
                    mouseMove = true;
                    triangle = false;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent arg0) {
                x2 = arg0.getX();
                y2 = arg0.getY();
                repaint();
            }

            public void mouseMoved(MouseEvent e) {
                if (mouseMove) {
                    x2 = e.getX();
                    y2 = e.getY();
                    repaint();
                }
            }
        });
    }

    /**
     * @return the data
     */
    public ArrayList<shape> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(ArrayList<shape> data) {
        this.data = data;
    }

    public void setDrawMode(int x) {
        selected = x;
        pts.clear();
        shapeAction = -1;
    }

    public void setMoveMode(boolean x) {
        if (x && shapeAction == -1)
            throw new RuntimeException();
        moveMode = x;
    }

    public void changeStrokeColor(Color c) {
        if (shapeAction == -1)
            throw new RuntimeException();
        left.push(new ArrayList<shape>(data));
        shape temp = data.get(shapeAction);
        ChangeColor get = new ChangeColor();
        shape x = get.Change(temp, c, false);
        data.remove(shapeAction);
        data.add(shapeAction, x);
        shapeAction = -1;
        repaint();
    }

    public void changeFillColor(Color c) {
        if (shapeAction == -1)
            throw new RuntimeException();
        left.push(new ArrayList<shape>(data));
        shape temp = data.get(shapeAction);
        ChangeColor get = new ChangeColor();
        shape x = get.Change(temp, c, true);
        data.remove(shapeAction);
        data.add(shapeAction, x);
        shapeAction = -1;
        repaint();
    }

    public void setResizeMode(boolean x) {
        if (x && shapeAction == -1)
            throw new RuntimeException();
        resizeMode = x;
    }

    public int getDrawMode() {
        return selected;
    }

    public int isClicked(double x, double y) {
        for (int i = data.size() - 1; i >= 0; --i) {
            if (data.get(i).pointInShape(x, y))
                return i;
        }
        return -1;
    }

    public void deleteShape() {
        if (shapeAction == -1)
            throw new RuntimeException();
        left.push(new ArrayList<shape>(data));
        data.remove(shapeAction);
        shapeAction = -1;
        repaint();
    }

    public void setOutColor(Color c) {
        outc = c;
    }

    public Color getoutColor() {
        return outc;
    }

    public void setFillColor(Color c) {
        fillc = c;
    }

    public Color getFillColor() {
        return fillc;
    }

    public void setStroke(int val) {
        strokeVal = val;
    }

    public int getStroke() {
        return strokeVal;
    }

    public void undo() {
        if (left.size() == 0)
            throw new RuntimeException();
        shapeAction = -1;
        ArrayList<shape> temp = new ArrayList<shape>(data);
        getRight().push(temp);
        data = new ArrayList<shape>(left.pop());
        repaint();
    }

    public void redo() {
        if (right.size() == 0)
            throw new RuntimeException();
        shapeAction = -1;
        ArrayList<shape> temp = new ArrayList<shape>(data);
        left.push(temp);
        data = new ArrayList<shape>(right.pop());
        repaint();
    }

    /**
     * @return the left
     */
    public Stack<ArrayList<shape>> getLeft() {
        return left;
    }

    /**
     * @param left
     *            the left to set
     */
    public void setLeft(Stack<ArrayList<shape>> left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public Stack<ArrayList<shape>> getRight() {
        return right;
    }

    /**
     * @param right
     *            the right to set
     */
    public void setRight(Stack<ArrayList<shape>> right) {
        this.right = right;
    }

    public BufferedImage getImg() {
        return curGrid;
    }

}
