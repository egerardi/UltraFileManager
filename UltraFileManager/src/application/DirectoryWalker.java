package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DirectoryWalker {
	
	List<File> directoryList;
	
	private void walk( String path ) throws IOException { 

        File root = new File( path ); 
        File[] list = root.listFiles(); 

        if (list == null) //if list of files is null exit function
        {
        	return;
        }
        
        for (int i = 0; i < list.length; i++) 
        {
        	if (list[i] != null) //if list item (a file) is not null
        	{       	
        		if ( ! list[i].isHidden() ) //if file is NOT hidden
	            {
		            if ( list[i].isDirectory() )
		            { 
		                walk( list[i].getAbsolutePath() );
		                directoryList.add(list[i]);
		                //System.out.print( "Dir: ");
		                //printFileDetails(list[i]);
		            }
	            }
        	}
        }
    } 

	private void printFileDetails(File file) throws IOException {
		BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		System.out.println(file.getName() + "\t" + attr.lastModifiedTime() + "\t" + attr.creationTime() + "\t" + getExtension(file) + "\t" + attr.size() + "\t Parent: " + file.getParent());
	}
	
	private String getExtension (File file) {
		String extension = file.getName();

		int i = extension.lastIndexOf('.');
		int p = Math.max(extension.lastIndexOf('/'), extension.lastIndexOf('\\'));

		if (i > p) 
		{
		    return extension.substring(i+1);
		}
		else 
		{
			return "File Folder";
		}
	}
	
	public List<File> getDirectoryList (String filepath) throws IOException {
		walk(filepath);
		return directoryList;
	}
	
    public DirectoryWalker () {
    	directoryList = new ArrayList<File>(1000);
    } 
}
