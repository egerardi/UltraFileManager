package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	static DirectoryWalker directoryWalker = new DirectoryWalker();
	static String directorypath = "C:\\Users\\Eric\\Documents";
	static List<File> directoryUnorderedList = new ArrayList<File>();
	static DirectoryPathsToTree directoryPathsToTree = new DirectoryPathsToTree();
	static List<Directory> directoryTreeList;
	
	@Override	
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
						
			
			VBox myVBox = createStackedTitledPanes();
			root.getChildren().add(myVBox);
			
			//Scene
			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ultra File Manager");
			primaryStage.show();
			
			for(int i = 0; i < directoryTreeList.size(); i++)
			{
				System.out.println(directoryTreeList.get(i).getPath() + "\t" + directoryTreeList.get(i).getTreeLevel());
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private VBox createStackedTitledPanes() {
		final VBox stackedTitledPanes = new VBox();
		Node contentNode1 = null;
		Node contentNode2 = null;
		Node contentNode3 = null;
		stackedTitledPanes.getChildren().setAll(
		    new TitledPane("Pane 1",  contentNode1),
		    new TitledPane("Pane 2",  contentNode2),
		    new TitledPane("Pane 3",  contentNode3)
		  );
		  ((TitledPane) stackedTitledPanes.getChildren().get(0)).setExpanded(true);

		  return stackedTitledPanes;
	}
	
	private static void loadNavigationPaneInfo () {
		try {
			directoryUnorderedList = directoryWalker.getDirectoryList(directorypath);
			directoryTreeList = directoryPathsToTree.getList(directoryUnorderedList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		loadNavigationPaneInfo();
		launch(args);
	}
}
