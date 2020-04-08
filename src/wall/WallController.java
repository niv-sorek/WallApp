package wall;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

import static Utils.WallUtils.isNumeric;

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
    public TextField txtCohesion;
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
    public Button btnIterate;
    private Wall model;

    public WallController() {
    }

    public void setModel(Wall model) {
        this.model = model;
    }

    @FXML
    public void initialize(Wall model) {
        this.setModel(model);
        txtGroundW.textProperty().bindBidirectional(model.gammaProperty(), new NumberStringConverter());
        txtInnerFrictionAngle.textProperty().bindBidirectional(model.fiProperty(), new NumberStringConverter());
        txtFrictionAngle.textProperty().bindBidirectional(model.lambdaProperty(), new NumberStringConverter());
        txtGroundAngle.textProperty().bindBidirectional(model.iProperty(), new NumberStringConverter());
        txtEffort.textProperty().bindBidirectional(model.maxEffortProperty(), new NumberStringConverter());
        txtBaseAngle.textProperty().bindBidirectional(model.thetaProperty(), new NumberStringConverter());
        txtFrictionCoeff.textProperty().bindBidirectional(model.miuProperty(), new NumberStringConverter());
        txtCohesion.textProperty().bindBidirectional(model.coProperty(), new NumberStringConverter());
        txtSpatialWeight.textProperty().bindBidirectional(model.qProperty(), new NumberStringConverter());
        txtWallWeight.textProperty().bindBidirectional(model.gwProperty(), new NumberStringConverter());
        txtFaceSlope.textProperty().bindBidirectional(model.faceSlopeProperty(), new NumberStringConverter());
        this.model.height.bind(txtH1, txtH2, txtH3, txtH4, txtH);
        this.model.weight.bind(txtW1, txtW2, txtW3, txtW4, txtW);
        for (Node node :
                dataGrid.getChildren()) {
            if (node instanceof TextField)
                ((TextField) node).textProperty().addListener((observableValue, s, t1) -> {
                    if (!t1.matches("\\d{0,4}([.]\\d{0,2})?")) {
                        ((TextField) node).setText(s);
                        return;
                    }
                    if (isNumeric(t1))
                        update();
                });
        }
        btnIterate.setOnAction(event -> {
            model.iterate();
            update();
        });

    }

    private void update() {
        model.calcWall();
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

