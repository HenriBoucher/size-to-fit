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
column1.setHgrow(Priority.ALWAYS);
root.getColumnConstraints().add(column1);

ColumnConstraints column2 = new ColumnConstraints();
column2.setHgrow(Priority.ALWAYS);
root.getColumnConstraints().add(column2);

Line head = new Line();
Line tail = new Line();
Rectangle pipe = new Rectangle();

GridPane.setHalignment(head, HPos.RIGHT);
GridPane.setValignment(head, VPos.CENTER);

GridPane.setHalignment(tail, HPos.LEFT);
GridPane.setValignment(tail, VPos.CENTER);

GridPane.setHalignment(pipe, HPos.LEFT);
GridPane.setValignment(pipe, VPos.CENTER);
pipe.setWidth(200);
pipe.setHeight(50);

root.getChildren().addAll(head, tail, pipe);

head.layoutXProperty().addListener(c -> {
    Bounds bHead = head.localToScene(head.getBoundsInLocal());
    Bounds bTail = tail.localToScene(tail.getBoundsInLocal());
    pipe.setWidth(bHead.getMinX() - bTail.getMaxX() - 50);
});

primaryStage.setScene(new Scene(root, 300, 250));
primaryStage.show();
}
} 