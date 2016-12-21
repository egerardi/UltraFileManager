package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryWalker {
	public void walk( String path ) throws IOException { 

        File root = new File( path ); 
        File[] list = root.listFiles(); 

        for ( File f : list ) { 
            if ( f.isDirectory() ) { 
                walk( f.getAbsolutePath() ); 
                System.out.print( "Dir: ");
                printFileDetails(f);
            } 
        } 
    } 

	private void printFileDetails(File file) throws IOException {
		BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		System.out.println(file.getName() + "\t" + attr.lastModifiedTime() + "\t" + attr.creationTime() + "\t" + getExtension(file) + "\t" + attr.size() );
	}
	
	private String getExtension (File file) {
		String extension = file.getName();

		int i = extension.lastIndexOf('.');
		int p = Math.max(extension.lastIndexOf('/'), extension.lastIndexOf('\\'));

		if (i > p) {
		    return extension.substring(i+1);
		}
		else {
			return "File Folder";
		}
	}
	
	
	
    public DirectoryWalker (String filepath) throws IOException {
        walk(filepath); 
    } 
}
