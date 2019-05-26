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
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import myE4Package.Pipe;

public class RenderFXML extends Application {

	@Override
	public void start(Stage primaryStage) {
		
//		ObjectBinding<Bounds> boundsInSceneBindingHead;
		ObjectBinding<Bounds> boundsInSceneBindingTail;
		
		List<Line> alist = new ArrayList<Line>();
		List<Pipe> pipeList = new ArrayList<Pipe>();
		
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
				pipeList.add(p);
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
				alist.add(head);
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
				
				final Line line = head;
				
				ObjectBinding<Bounds> boundsInSceneBindingHead = Bindings.createObjectBinding(() -> {
					Bounds nodeLocal = line.getBoundsInLocal();
					Bounds nodeScene = line.localToScene(nodeLocal);
					return nodeScene;
				}, head.boundsInLocalProperty());
//				p.widthProperty().bind(boundsInSceneHead.subtract(head.layoutXProperty()));

				System.out.println(headId);
				System.out.println(tailId);
			}
		}
		);
		Line tempLine = new Line();
		alist.add(tempLine);
		System.out.println(alist);
		System.out.println(alist.size());
		final List<Line> imlist = Collections.unmodifiableList(alist);
		System.out.println(imlist.get(0));
		
		ObjectBinding<Bounds> boundsInSceneBindingHead = Bindings.createObjectBinding(() -> {
			Bounds nodeLocal = imlist.get(0).getBoundsInLocal();
			Bounds nodeScene = imlist.get(0).localToScene(nodeLocal);
			return nodeScene;
		}, imlist.get(0).boundsInLocalProperty());
		
		pipeList.get(0).widthProperty().bind(Bindings.createDoubleBinding(() -> boundsInSceneBindingHead.get().getMinX(), boundsInSceneBindingHead));
		
//		Scene scene = new Scene(root, 600, 600);
		
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
