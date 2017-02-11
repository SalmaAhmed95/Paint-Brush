/**
 * 
 */
package Files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Shapes.shape;
public class JsonWriter {
	public static JSONObject JsonObj = new JSONObject();
	public static JSONArray shapes = new JSONArray();

	public void Jsonwriter(ArrayList<shape> data,Stack<ArrayList<shape>>left,Stack<ArrayList<shape>>right,String filePath) {
		for (int i = 0; i < data.size(); i++) {
			shape current = data.get(i);
			current.createJson(i);
		}
		try {
			FileWriter file = new FileWriter(filePath);
			JsonObj.put("Shapes",shapes);
			file.write(JsonObj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
		}
	}
}
