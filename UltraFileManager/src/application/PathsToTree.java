package application;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class PathsToTree {
	
	static int count = -1;
	
    public static class Node {
        private final Map<String, Node> children = new TreeMap<>();

        public Node getChild(String name) {
            if (children.containsKey(name))
                return children.get(name);
            Node result = new Node();
            children.put(name, result);
            return result;
        }

        public Map<String, Node> getChildren() {
            return Collections.unmodifiableMap(children);
        }
    }

    private final Node root = new Node();

    private static final Pattern PATH_SEPARATOR = Pattern.compile("\\\\");
    public void addPath(String path) {
        String[] names = PATH_SEPARATOR.split(path);
        Node node = root;
        for (String name : names)
            node = node.getChild(name);
    }

    private static void printHtml(Node node, PrintStream out) {
        Map<String, Node> children = node.getChildren();
        if (children.isEmpty())
            return;
        for (int j = 0; j < count; j++)
        {
        	out.print("\t");
        }
        count++;
        out.println("<ul>");
        for (Map.Entry<String, Node> child : children.entrySet()) {
        	 for (int j = 0; j <= count; j++)
             {
             	out.print("\t");
             }
            out.print("<li>");
            out.print(child.getKey() + "\t" + count);
            out.println();
            printHtml(child.getValue(), out);
            for (int j = 0; j <= count; j++)
            {
            	out.print("\t");
            }
            out.println("</li>");
        }
        count--;
        for (int j = 0; j < count; j++)
        {
        	out.print("\t");
        }
        out.println("</ul>");
    }

    public void printHtml(PrintStream out) {
        printHtml(root, out);
    }

    public PathsToTree(String[] pathlist) {
        for(int i = 0; i < pathlist.length; i++)
    	{
    		addPath(pathlist[i]);
    	}
        
        printHtml(System.out);
    }
}