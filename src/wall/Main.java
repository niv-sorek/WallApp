package wall;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
    private static Wall model;

    private static WallGraphics sketch;

    public static void main(String[] args) {
        model = new Wall();
        sketch = new WallGraphics(model);
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(Wall.class.getResource("/wall/view.fxml"));
//
//        WallController controller = new WallController();
//        controller.setModel(model);
//        loader.setController(controller);
//        Parent root = loader.load();
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//
//
//        primaryStage.show();


        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "view.fxml"));
        BorderPane root = loader.load();
        WallController ctrl = loader.getController();
        ctrl.initialize(model);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        StackPane sp = new StackPane();
        sp.getChildren().add(sketch);
        root.centerProperty().set(sp);

        primaryStage.show();
        ctrl.setSketch(sketch);
        //etch.setWidth();
    }
}

