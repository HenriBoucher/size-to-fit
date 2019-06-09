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
import javafx.scene.Parent;

public class Pane extends Application {
	
	class InternalPipe {
		Pipe p;
		Line head;
		Line tail;
	}
	
	int pipeArraySize = 2;
	int lineArraySize = 2;
	final int arrayIncrement = 2;
	InternalPipe[] pipeArray = new InternalPipe[pipeArraySize];
	Line[] lineArray = new Line[lineArraySize];
	
	int pipeIndex = 0;
	int lineIndex = 0;
	
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
	
	final Scene scene = new Scene(root, 600, 600);
	primaryStage.setScene(scene);
	primaryStage.show();
	
	root.getChildren().forEach(child -> {
		if (child instanceof Pipe) {
			Pipe p = (Pipe) child;
			pipeArray[pipeIndex] = new InternalPipe();
			pipeArray[pipeIndex].p = p;
			
			String headId = "-pipe-" + p.getHeadColumn() + "-" + p.getHeadHPos() + "-" + p.getHeadRow() + "-" + p.getHeadVPos();
			Line head = (Line) scene.lookup("#" + headId);
			if (head == null) {
				head = new Line();
				head.setId(headId);
				GridPane.setColumnIndex(head, p.getHeadColumn());
				GridPane.setHalignment(head, p.getHeadHPos());
				GridPane.setRowIndex(head, p.getHeadRow());
				GridPane.setValignment(head, p.getHeadVPos());
				lineArray[lineIndex++] = head;
				if (lineIndex == lineArraySize) {
					Line[] tempLineArray = new Line[lineArraySize + arrayIncrement];
					System.arraycopy(lineArray, 0, tempLineArray, 0, lineArraySize);
					lineArray = tempLineArray;
					lineArraySize = lineArray.length;
				}
			} 
			pipeArray[pipeIndex].head = head;

			String tailId = "-pipe-" + p.getTailColumn() + "-" + p.getTailHPos() + "-" + p.getTailRow() + "-" + p.getTailVPos();
			Line tail = (Line) scene.lookup("#" + tailId);
			if (tail == null) {
				tail = new Line();
				tail.setId(tailId );
				GridPane.setColumnIndex(tail, p.getTailColumn());
				GridPane.setHalignment(tail, p.getTailHPos());
				GridPane.setRowIndex(tail, p.getTailRow());
				GridPane.setValignment(tail, p.getTailVPos());
				lineArray[lineIndex++] = tail;
				if (lineIndex == lineArraySize) {
					Line[] tempLineArray = new Line[lineArraySize + arrayIncrement];
					System.arraycopy(lineArray, 0, tempLineArray, 0, lineArraySize);
					lineArray = tempLineArray;
					lineArraySize = lineArray.length;
				}
			}
			pipeArray[pipeIndex].tail = tail;
			
			//TODO must handle array resize
			pipeIndex++;
			if (pipeIndex == pipeArraySize) {
				System.out.println("pipeIndex " + pipeIndex + " arraySize " + pipeArraySize);
				InternalPipe[] tempPipeArray = new InternalPipe[pipeArraySize + arrayIncrement];
				System.arraycopy(pipeArray, 0, tempPipeArray, 0, pipeArraySize);
				pipeArray = tempPipeArray;
				pipeArraySize = pipeArray.length;
				for (int i = 0; i < pipeIndex; i++) {
					System.out.println("int = " + i + " tailId = " + pipeArray[i].tail + " headId = " + pipeArray[i].head);					
				}
			}
	}
	});
	
	for (int i = 0; i < lineIndex; i++) {
		root.getChildren().add(lineArray[i]);
		System.out.println("adding line " + lineArray[i]);
	}
	root.requestLayout();
	
	String mystring = "-pipe-1-LEFT-1-CENTER";
	Line test = (Line) scene.lookup("#" + mystring );

	System.out.println("~~~~~~~~~~~~~~ " + test);
	
	processAnchors();
	
	root.widthProperty().addListener(new ChangeListener<Number>() {
	    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
	        System.out.println("Width: " + newSceneWidth);
	        processAnchors();
	    }
	});
	root.heightProperty().addListener(new ChangeListener<Number>() {
	    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
	        System.out.println("Height: " + newSceneHeight);
	        processAnchors();
	    }
	});
	
	}
	
	
	void processAnchors(){
		for (int i = 0; i < pipeIndex; i++) {
			System.out.println("i in processAnchors = " + i + " tail = " + pipeArray[i].tail);
	        Bounds boundsInSceneTail = pipeArray[i].tail.localToScene(pipeArray[i].tail.getBoundsInLocal());
			Bounds boundsInSceneHead = pipeArray[i].head.localToScene(pipeArray[i].head.getBoundsInLocal());
			pipeArray[i].p.setWidth(boundsInSceneHead.getMinX() - boundsInSceneTail.getMinX());
		}
}
	
	public static void main(String[] args) {
		launch(args);
	}

}
