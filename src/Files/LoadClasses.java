package Files;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JOptionPane;

import Shapes.shape;

public class LoadClasses {

    private Constructor<?>[] curConst;

    public boolean load(String classPath) {
        File file = new File(classPath);
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[] {url};
            @SuppressWarnings("resource")
            ClassLoader cl = new URLClassLoader(urls);
            Class<?> cls = cl.loadClass("Shapes.Square");
            curConst = cls.getConstructors();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Select Valid Class");
            return false;
        }
    }

    public shape getObject(double newX, double newY, double length,
            Color newfill, Color newout,int newStroke) {
        try {
            Object obj = curConst[0].newInstance(newX, newY, length, newfill,
                    newout,newStroke);
            return (shape) obj;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Select Valid Class");
        }
        return null;
    }

    public boolean isSquare(Object obj) {
    	String s1 = obj.getClass().getSimpleName();
    	String s2 = curConst[0].getName().substring(curConst[0].getName().indexOf(".") + 1);
    	return s1.equals(s2);
    	}
}
