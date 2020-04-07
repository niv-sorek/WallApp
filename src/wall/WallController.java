package wall;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

public class WallController {

    // public Label helloWorld;
    @FXML
    public GridPane dataGrid;
    @FXML
    public TextField txtGroundW;
    @FXML
    public TextField txtWallWeight;
    @FXML
    public TextField txtInnerFrictionAngle;
    @FXML
    public TextField txtFrictionAngle;
    @FXML
    public TextField txtGroundAngle;
    @FXML
    public TextField txtEffort;
    @FXML
    public TextField txtBaseAngle;
    @FXML
    public TextField txtFrictionCoeff;
    @FXML
    public TextField txtCohessia;
    @FXML
    public TextField txtSpatialWeight;
    @FXML
    public GridPane gridHeights;
    @FXML
    public GridPane gridWidth;
    @FXML
    public TextField txtW;
    @FXML
    public TextField txtW1;
    @FXML
    public TextField txtW2;
    @FXML
    public TextField txtW3;
    @FXML
    public TextField txtW4;
    @FXML
    public TextField txtH;
    @FXML
    public TextField txtH1;
    @FXML
    public TextField txtH2;
    @FXML
    public TextField txtH3;
    @FXML
    public TextField txtH4;
    public Text txtPa;
    public TextField txtFaceSlope;
    public Label txtQh;
    public Label txtSmt;
    public Label txtSh;
    public Label txtMt2;
    public Label txtMt1;
    public Text txtKa;
    public Label txtPh;
    public TextField txtTeta;
    private Wall model;

    public WallController() {
    }

    public void setModel(Wall model) {
        this.model = model;
    }

    public void KeyPressed(KeyEvent keyEvent) {
    }

    @FXML
    public void initialize(Wall model) {
        this.setModel(model);
        txtGroundW.textProperty().bindBidirectional(model.groundWeightProperty(), new NumberStringConverter());
        txtInnerFrictionAngle.textProperty().bindBidirectional(model.innerFrictionAngleProperty(), new NumberStringConverter());
        txtFrictionAngle.textProperty().bindBidirectional(model.groundFrictionAngleProperty(), new NumberStringConverter());
        txtGroundAngle.textProperty().bindBidirectional(model.groundAngleProperty(), new NumberStringConverter());
        txtEffort.textProperty().bindBidirectional(model.maxEffortProperty(), new NumberStringConverter());
        txtBaseAngle.textProperty().bindBidirectional(model.baseAngleProperty(), new NumberStringConverter());
        txtFrictionCoeff.textProperty().bindBidirectional(model.frictionCoeffProperty(), new NumberStringConverter());
        txtCohessia.textProperty().bindBidirectional(model.cohessiaProperty(), new NumberStringConverter());
        txtSpatialWeight.textProperty().bindBidirectional(model.spatialWeightProperty(), new NumberStringConverter());
        txtWallWeight.textProperty().bindBidirectional(model.wallWeightProperty(), new NumberStringConverter());
        txtFaceSlope.textProperty().bindBidirectional(model.faceSlopeProperty(), new NumberStringConverter());
        this.model.height.bind(txtH1, txtH2, txtH3, txtH4, txtH);
        this.model.weight.bind(txtW1, txtW2, txtW3, txtW4, txtW);
        for (Node node :
                dataGrid.getChildren()) {
            if (node instanceof TextField)
                ((TextField) node).textProperty().addListener((observableValue, s, t1) -> {
                    if (!t1.matches("\\d{0,4}([\\.]\\d{0,2})?")) {
                        ((TextField) node).setText(s);
                        return;
                    }
                    if (isNumeric(t1))
                        update();
                });
        }

    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private void update() {
        model.calc();
        txtPa.setText(String.format("%.2f", model.getPa()));
        txtPh.setText(String.format("%.2f", model.getPh()));
        txtQh.setText(String.format("%.2f", model.getQh()));
        txtSh.setText(String.format("%.2f", model.getSh()));

        txtMt1.setText(String.format("%.2f", model.getMt1()));
        txtMt2.setText(String.format("%.2f", model.getMt2()));
        txtSmt.setText(String.format("%.2f", model.getSMt()));
        txtKa.setText(String.format("%.2f", model.getKa()));
    }
}

