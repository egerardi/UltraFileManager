package lame;

import java.io.IOException; 
import java.net.URISyntaxException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class lamer {

	public static void main(String[] args) throws URISyntaxException, IOException, AccessDeniedException  {
		
		Path path = Paths.get("C:\\Users\\Eric\\");
		Stream<Path> fileStream = Files.walk(path)
        	.filter(Files::isRegularFile);
        	//.forEach(System.out::println);
		
		System.out.println();
	}

}
