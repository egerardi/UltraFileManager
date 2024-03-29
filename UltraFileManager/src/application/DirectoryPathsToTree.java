package application;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class DirectoryPathsToTree {
	
	static List<Directory> directoryList;
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

    private static void createList (Node node) {
        Map<String, Node> children = node.getChildren();
        if (children.isEmpty())
        {
            return;
        }
        
        count++;
        
        for (Map.Entry<String, Node> child : children.entrySet()) {
        	Directory dir = new Directory(child.getKey(), count);
        	directoryList.add(dir);
            createList(child.getValue());
        }
        count--;
    }

    public List<Directory> getList (List<File> pathlist) {
        for(int i = 0; i < pathlist.size(); i++)
    	{
    		addPath(pathlist.get(i).getPath());
    	}
        createList(root);
        
        return directoryList;
    }
    
    
    public DirectoryPathsToTree() {
    	directoryList = new ArrayList<>(1000);
    }
}