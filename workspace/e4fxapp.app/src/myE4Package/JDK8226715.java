package myE4Package;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Bounds;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

public class JDK8226715 extends Application {
public static void main(String []args) {
launch(args);
}

@Override
public void start(Stage primaryStage) {

primaryStage.setTitle("JDK8226715");
GridPane root = new GridPane();
root.setAlignment(Pos.CENTER);
root.setGridLinesVisible(true);

ColumnConstraints column1 = new ColumnConstraints();
column1.setMinWidth(10.0);
column1.setHgrow(Priority.ALWAYS);
root.getColumnConstraints().add(column1);

ColumnConstraints column2 = new ColumnConstraints();
column2.setMinWidth(10.0);
column2.setHgrow(Priority.ALWAYS);
root.getColumnConstraints().add(column2);

Line head = new Line();
Line tail = new Line();
Rectangle pipe = new Rectangle();

GridPane.setColumnIndex(head, 0);
GridPane.setHalignment(head, HPos.RIGHT);
GridPane.setValignment(head, VPos.CENTER);

GridPane.setColumnIndex(tail, 0);
GridPane.setHalignment(tail, HPos.LEFT);
GridPane.setValignment(tail, VPos.CENTER);

GridPane.setColumnIndex(pipe, 0);
GridPane.setHalignment(pipe, HPos.LEFT);
GridPane.setValignment(pipe, VPos.CENTER);
pipe.setWidth(200);
pipe.setHeight(50);

root.getChildren().addAll(head, tail, pipe);

Line head2 = new Line();
Line tail2 = new Line();
Rectangle pipe2 = new Rectangle();

GridPane.setColumnIndex(head2, 1);
GridPane.setHalignment(head2, HPos.RIGHT);
GridPane.setValignment(head2, VPos.CENTER);

GridPane.setColumnIndex(tail2, 1);
GridPane.setHalignment(tail2, HPos.LEFT);
GridPane.setValignment(tail2, VPos.CENTER);

GridPane.setColumnIndex(pipe2, 1);
GridPane.setHalignment(pipe2, HPos.LEFT);
GridPane.setValignment(pipe2, VPos.CENTER);
pipe2.setWidth(200);
pipe2.setHeight(50);

root.getChildren().addAll(head2, tail2, pipe2);

head.layoutXProperty().addListener(c -> {
    Bounds bHead = head.localToScene(head.getBoundsInLocal());
    Bounds bTail = tail.localToScene(tail.getBoundsInLocal());
    pipe.setWidth(bHead.getMinX() - bTail.getMaxX() - 50);
});

head2.layoutXProperty().addListener(c -> {
    Bounds bHead = head2.localToScene(head2.getBoundsInLocal());
    Bounds bTail = tail2.localToScene(tail2.getBoundsInLocal());
    pipe.setWidth(bHead.getMinX() - bTail.getMaxX() - 50);
});

primaryStage.setScene(new Scene(root, 300, 250));
primaryStage.show();
}
} 