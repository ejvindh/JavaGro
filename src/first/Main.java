package first;
	
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

	public Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			Preferences userPrefs = Preferences.userNodeForPackage(getClass());
	        double x = userPrefs.getDouble("stage.x", 100);
	        double y = userPrefs.getDouble("stage.y", 100);
	        double w = userPrefs.getDouble("stage.width", 620);
	        double h = userPrefs.getDouble("stage.height", 400);

			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("JavagroFxml.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setX(x);
			primaryStage.setY(y);
			primaryStage.setWidth(w);
			primaryStage.setHeight(h);
			primaryStage.setTitle("JavaGro");
			primaryStage.getIcons().add(new Image(getClass().
					getResourceAsStream("graphics/gro_icon.png")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    @Override
    public void stop() {
        
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        userPrefs.putDouble("stage.x", primaryStage.getX());
        userPrefs.putDouble("stage.y", primaryStage.getY());
        userPrefs.putDouble("stage.width", primaryStage.getWidth());
        userPrefs.putDouble("stage.height", primaryStage.getHeight());
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
