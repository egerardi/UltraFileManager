package application;

public class Directory {
	private String path;
	private int treeLevel;
	
	public String getPath() {
		return path;
	}
	public int getTreeLevel() {
		return treeLevel;
	}
	
	public Directory (String p, int t) {
		path = p;
		treeLevel = t;		
	}
}
