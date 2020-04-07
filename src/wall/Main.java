package wall;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args) {

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
        Parent root = loader.load();
        WallController ctrl = loader.getController();
        ctrl.initialize(new Wall());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }
}
