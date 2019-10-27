package myE4Package;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.annotation.PostConstruct;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class E4pane {
	
	
	@PostConstruct
	void initUI(BorderPane pane) {
		
		GridPane root = null;
		try {
			root = (GridPane) FXMLLoader.load(getClass().getResource("test.fxml"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		root.prefHeightProperty().bind(pane.heightProperty());
		root.prefWidthProperty().bind(pane.widthProperty());

		pane.widthProperty().addListener((obs) -> { System.out.println(obs); });
		pane.heightProperty().addListener((obs) -> { System.out.println(obs); });
		
		root.setGridLinesVisible(true);
		pane.setCenter(root);

	}

}
