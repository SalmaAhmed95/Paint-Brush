/**
 * 
 */
package Files;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Shapes.shape;

public class XmlWriter {

    public static DocumentBuilderFactory docFactory;
    public static DocumentBuilder docBuilder;
    public static Document doc;

    public void writeXml(ArrayList<shape> data, Stack<ArrayList<shape>> left,
            Stack<ArrayList<shape>> right, String filePath) {
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            Element title = doc.createElement("Shapes");
            doc.appendChild(title);
            for (int i = 0; i < data.size(); i++) {
                shape currshape = data.get(i);
                Element shapeattr = currshape.createXml(i);
                title.appendChild(shapeattr);

            }
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            // System.out.println("File saved successfully");
        } catch (ParserConfigurationException e) {
        } catch (TransformerConfigurationException e) {
        } catch (TransformerException e) {
        }
    }

}
