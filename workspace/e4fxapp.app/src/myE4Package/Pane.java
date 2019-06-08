package myE4Package;

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
import javafx.scene.shape.Rectangle;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

public class Pane extends Application {
	class InternalPipe {
		Pipe p;
		Line head;
		Line tail;
	}
	InternalPipe[] pipeArray = new InternalPipe[100];
	int pipeIndex = 0;
	Pipe p = new Pipe();
	Line head = new Line();
	Line tail = new Line();
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
	

	
	root.getChildren().forEach(child -> {
		if (child instanceof Pipe) {
			p = (Pipe) child;
			pipeArray[pipeIndex] = new InternalPipe();
			pipeArray[pipeIndex].p = p;
			
			String headId = "-pipe-" + p.getHeadColumn() + "-" + p.getHeadHPos() + "-" + p.getHeadRow() + "-" + p.getHeadVPos();
			head = (Line) scene.lookup("#" + headId);
			if (head == null) {
				head = new Line();
				head.setId(headId);
				GridPane.setColumnIndex(head, p.getHeadColumn());
				GridPane.setHalignment(head, p.getHeadHPos());
				GridPane.setRowIndex(head, p.getHeadRow());
				GridPane.setValignment(head, p.getHeadVPos());
			}
			pipeArray[pipeIndex].head = head;

			String tailId = "-pipe-" + p.getTailColumn() + "-" + p.getTailHPos() + "-" + p.getTailRow() + "-" + p.getTailVPos();
			tail = (Line) scene.lookup("#" + tailId);
			if (tail == null) {
				tail = new Line();
				tail.setId(headId);
				GridPane.setColumnIndex(tail, p.getTailColumn());
				GridPane.setHalignment(tail, p.getTailHPos());
				GridPane.setRowIndex(tail, p.getTailRow());
				GridPane.setValignment(tail, p.getTailVPos());
			}
			pipeArray[pipeIndex].tail = tail;
			
			pipeIndex++;
	}
	});
	
	for (int i = 0; i < pipeIndex; i++) {
		root.getChildren().addAll(pipeArray[i].head, pipeArray[i].tail);
	}
	
	root.widthProperty().addListener(new ChangeListener<Number>() {
	    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
	        System.out.println("Width: " + newSceneWidth);
	        Bounds boundsInSceneTail = tail.localToScene(tail.getBoundsInLocal());
			Bounds boundsInSceneHead = head.localToScene(head.getBoundsInLocal());
			p.setWidth(boundsInSceneHead.getMinX() - boundsInSceneTail.getMinX());
	    }
	});
	root.heightProperty().addListener(new ChangeListener<Number>() {
	    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
	        System.out.println("Height: " + newSceneHeight);
	    }
	});
	}
	public static void main(String[] args) {
		launch(args);
	}

}
