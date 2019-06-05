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
			String headId = "#-pipe-" + p.getHeadColumn() + "-" + p.getHeadHPos() + "-" + p.getHeadRow() + "-" + p.getHeadVPos();
			head = (Line) scene.lookup(headId);
			String tailId = "#-pipe-" + p.getTailColumn() + "-" + p.getTailHPos() + "-" + p.getTailRow() + "-" + p.getTailVPos();
			tail = (Line) scene.lookup(tailId);
	}
	});
	
	
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
