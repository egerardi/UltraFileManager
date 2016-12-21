package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	//Filewalker filewalker;
	//String filepath = "D:\\Eric\\Music\\Boston\\";
	
	DirectoryWalker directoryWalker = new DirectoryWalker();
	String directorypath = "C:\\Users\\Eric\\Documents";
	List<File> directoryList = new ArrayList<File>();
	
	//DirectoryPathsToTree directoryPathsToTree;
	
	@Override	
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			//Scene
			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ultra File Manager");
			primaryStage.show();
			
			//filewalker = new Filewalker(filepath);
			
			directoryList = directoryWalker.getDirectoryList(directorypath);
			
			for(int i = 0; i < directoryList.size(); i++)
			{
				System.out.println(directoryList.get(i).getName());
			}
			
			//String [] pathlist = {"C:\\Music\\Blur\\Leisure", "C:\\Music\\KateBush\\WholeStory\\Disc1", "C:\\Music\\KateBush\\WholeStory\\Disc2", "C:\\Music\\KateBush\\TheKickInside", "C:\\Music\\KateBush\\TheDreaming", "C:\\MusicUnprocessed\\Blue\\ParkLife"};
			
			//directoryPathsToTree = new DirectoryPathsToTree(pathlist);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
