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
		g.setGridLinesVisible(true);
		root.getChildren().forEach(child -> {
			if (child instanceof Pipe) {
				Pipe p = (Pipe) child;
				System.out.println("parent = " + p.getParent().getId());
				System.err.println("in RenderFXML " + p.getId());
				String headId = "-pipe-" + p.getHeadColumn() + "-" + p.getHeadHPos() + "-" + p.getHeadRow() + "-" + p.getHeadVPos();
				Line head = (Line) g.lookup(headId);
				if (head == null) {
					Line h = new Line();
					GridPane.setConstraints(h, p.getHeadColumn(), p.getHeadRow(), 1, 1, p.getHeadHPos(), p.getHeadVPos());
					h.setId(headId);
					head = h;
				}
				head.startXProperty().bind(p.xProperty());
//				p.xProperty().bind(head.startXProperty());  // you can't do both
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
