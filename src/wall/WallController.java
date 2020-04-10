package wall;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;

import static utils.WallUtils.isNumeric;

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
    public TextField txtTheta;
    public Button btnIterate;
    public VBox DimensionsVBox;
    private Wall model;
    private WallGraphics sketch;

    public WallController() {
    }

    public void setModel(Wall model) {
        this.model = model;
    }

    public void setSketch(WallGraphics sketch) {
        this.sketch = sketch;
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
        ArrayList<TextField> tfields = new ArrayList<TextField>();
        for (Node node :
                dataGrid.getChildren()) {
            if (node instanceof TextField)
                tfields.add((TextField) node);
        }
        for (Node node :
                gridHeights.getChildren()) {
            if (node instanceof TextField)
                tfields.add((TextField) node);
        }
        for (Node node :
                gridWidth.getChildren()) {
            if (node instanceof TextField)
                tfields.add((TextField) node);
        }
        for (TextField tf : tfields)
            tf.textProperty().addListener((observableValue, s, t1) -> {
                if (t1.length()>0 &&(!t1.matches("\\d{0,4}?([.]\\d{0,3})?") || !isNumeric(t1))) {
                    tf.setText(s);
                    return;
                }
                else if(t1.length()==0)
                    return;
                else
                    update();
            });
        btnIterate.setOnAction(event -> {
            model.iterate();
            update();
        });

    }

    private void update() {
        model.calcWall();
        sketch.sketch();
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

