package myE4Package;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.geometry.Bounds;

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
		
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		final GridPane g = root;
		g.setGridLinesVisible(true);
		g.getChildren().forEach(child -> {
			if (child instanceof Line) {
				System.out.println("css id = " + child.getId());
			}
			if (child instanceof Pipe) {
				Pipe p = (Pipe) child;
				System.out.println("parent = " + p.getParent().getId());
				System.err.println("in RenderFXML " + p.getId());
				String headId = "#-pipe-" + p.getHeadColumn() + "-" + p.getHeadHPos() + "-" + p.getHeadRow() + "-" + p.getHeadVPos();
				Line head = (Line) scene.lookup(headId);
				System.out.println(head);
				if (head == null) {
					Line h = new Line();
					GridPane.setConstraints(h, p.getHeadColumn(), p.getHeadRow(), 1, 1, p.getHeadHPos(), p.getHeadVPos());
					h.setStrokeWidth(10);
					Rectangle rect = new Rectangle();
					
					h.setId(headId);
					head = h;
				}
//				head.startXProperty().bind(p.xProperty());
				System.out.println(head);
				p.xProperty().bind(head.startXProperty());  // you can't do both
				String tailId = "#-pipe-" + p.getTailColumn() + "-" + p.getTailHPos() + "-" + p.getTailRow() + "-" + p.getTailVPos();
				Line tail = (Line) scene.lookup(tailId);
//				p.widthProperty().bind(tail.layoutXProperty().subtract(head.layoutXProperty()));
				
				Bounds boundsInSceneTail = tail.localToScene(tail.getBoundsInLocal());
				Bounds boundsInSceneHead = head.localToScene(head.getBoundsInLocal());
				System.out.println("tail = " + boundsInSceneTail);
				System.out.println("head = " + boundsInSceneHead);
				p.setWidth(boundsInSceneHead.getMinX() - boundsInSceneTail.getMinX());
//				p.widthProperty().bind(boundsInSceneHead.subtract(head.layoutXProperty()));

				System.out.println(headId);
				System.out.println(tailId);
			}
		});
		
		
//		Scene scene = new Scene(root, 600, 600);
		
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
