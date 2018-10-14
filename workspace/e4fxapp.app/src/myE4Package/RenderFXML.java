package myE4Package;

import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class RenderFXML extends Application {

	@Override
	public void start(Stage primaryStage) {
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
		final GridPane g = root;
		root.getChildren().forEach(pipe -> {
			if (pipe instanceof Pipe) {
				Pipe p = (Pipe) pipe;
				System.out.println("parent = " + pipe.getParent().getId());
				System.err.println("in RenderFXML " + pipe.getId());
				String headId = "-pipe-" + p.getHeadColumn() + "-" + p.getHeadHPos() + "-" + p.getHeadRow() + "-" + p.getHeadVPos();
				Line head = (Line) g.lookup(headId);
				if (head == null) {
					Line h = new Line();
					GridPane.setConstraints(h, p.getHeadColumn(), p.getHeadRow(), 1, 1, p.getHeadHPos(), p.getHeadVPos());
					head = h;
				}
				String tailId = "-pipe-" + p.getTailColumn() + "-" + p.getTailHPos() + "-" + p.getTailRow() + "-" + p.getTailVPos();
				System.out.println(headId);
			}
		});
		
		
		Scene scene = new Scene(root, 600, 600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
