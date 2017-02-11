/**
 * 
 */
package DrawTools;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;

import Shapes.shape;

public interface GridInterface {
	public void paintComponent(Graphics g);

	public ArrayList<shape> getData();

	public void setData(ArrayList<shape> data);

	public void setDrawMode(int x);

	public void setMoveMode(boolean x);

	public void changeStrokeColor(Color c);

	public void changeFillColor(Color c);

	public void setResizeMode(boolean x);

	public int getDrawMode();

	public int isClicked(double x, double y);

	public void deleteShape();

	public void setOutColor(Color c);

	public Color getoutColor();

	public void setFillColor(Color c);

	public Color getFillColor();

	public void setStroke(int val);

	public int getStroke();

	public void undo();

	public void redo();

	public Stack<ArrayList<shape>> getLeft();

	public void setLeft(Stack<ArrayList<shape>> left);

	public void setRight(Stack<ArrayList<shape>> right);

}
