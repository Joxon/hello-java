package edu.uci.swe245p_gui.ex31_applying_fxml_to_ex11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ShapeTransformationsFXML extends Application {

  private static final int MIN_WINDOW_WIDTH = 800;
  private static final int MIN_WINDOW_HEIGHT = 600;

  private String fxmlName = "ShapeTransformations.fxml";
  private Scene scene;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    final var content = (BorderPane) FXMLLoader.load(getClass().getResource(fxmlName));
    scene = new Scene(content, MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT);

    stage.setScene(scene);
    stage.setTitle("Shape Transformations");
    stage.setMinHeight(MIN_WINDOW_HEIGHT);
    stage.setMinWidth(MIN_WINDOW_WIDTH);
    stage.setHeight(MIN_WINDOW_HEIGHT);
    stage.setWidth(MIN_WINDOW_WIDTH);

    if (getClass().getSimpleName().equals("ShapeTransformationsFXML")) {
      stage.show();
    }
  }

  public String getFxmlName() {
    return fxmlName;
  }

  public void setFxmlName(String fxmlName) {
    this.fxmlName = fxmlName;
  }

  public Scene getScene() {
    return scene;
  }
}
