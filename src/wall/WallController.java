package wall;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
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
    public Label txtMrv;
    public Label txtMrw;
    public Label txtMrs;
    public Label txtMrq;
    public Label txtMrp;
    public Label txtSMr;
    public Label txtPv;
    public Label txtVw;
    public Label txtVs;
    public Label txtPp;
    public Label txtVq;
    public Text txtAS;
    public Text txtFSS;
    public Text txtFST;
    public Text txtE;
    public Text txtAngle;
    public CheckBox isPP;
    public BorderPane MainPane;
    private Wall model;
    private WallGraphics sketch;

    public WallController() {
    }

    public Wall getModel() {
        return model;
    }

    public void setModel(Wall model) {
        this.model = model;
    }

    public void setSketch(WallGraphics sketch) {
        this.sketch = sketch;
    }
    public WallGraphics getSketch( ) {
        return this.sketch;
    }

    @FXML
    public void initialize(Wall model) {
        this.setModel(model);
        txtGroundW.textProperty().bindBidirectional(model.gammaProperty(), new NumberStringConverter());
        txtInnerFrictionAngle.textProperty().bindBidirectional(model.fiProperty(), new NumberStringConverter());
        txtFrictionAngle.textProperty().bindBidirectional(model.lambdaProperty(), new NumberStringConverter());
        txtGroundAngle.textProperty().bindBidirectional(model.iProperty(), new NumberStringConverter());
        txtEffort.textProperty().bindBidirectional(model.sigmaProperty(), new NumberStringConverter());
        txtBaseAngle.textProperty().bindBidirectional(model.alphaProperty(), new NumberStringConverter());
        txtFrictionCoeff.textProperty().bindBidirectional(model.miuProperty(), new NumberStringConverter());
        txtCohesion.textProperty().bindBidirectional(model.coProperty(), new NumberStringConverter());
        txtSpatialWeight.textProperty().bindBidirectional(model.qProperty(), new NumberStringConverter());
        txtWallWeight.textProperty().bindBidirectional(model.gwProperty(), new NumberStringConverter());
        txtFaceSlope.textProperty().bindBidirectional(model.faceSlopeProperty(), new NumberStringConverter());
        this.model.height.bind(txtH1, txtH2, txtH3, txtH4, txtH);
        this.model.width.bind(txtW1, txtW2, txtW3, txtW4, txtW);
        isPP.selectedProperty().bindBidirectional(model.isPPProperty());
        ArrayList<TextField> tFields = new ArrayList<TextField>();
        for (Node node :
                dataGrid.getChildren()) {
            if (node instanceof TextField)
                tFields.add((TextField) node);
        }
        for (Node node :
                gridHeights.getChildren()) {
            if (node instanceof TextField)
                tFields.add((TextField) node);
        }
        for (Node node :
                gridWidth.getChildren()) {
            if (node instanceof TextField)
                tFields.add((TextField) node);
        }
        isPP.setOnAction(event -> update());
        for (TextField tf : tFields)
            tf.textProperty().addListener((observableValue, s, t1) -> {
                if (t1.length() > 0 && (!t1.matches("\\d{0,4}?([.]\\d{0,3})?") || !isNumeric(t1))) {
                    tf.setText(s);
                } else if (t1.length() == 0) {
                } else
                    update();
            });
        btnIterate.setOnAction(event -> {
            model.iterate();
            update();
        });

    }

    private void update() {
        this.sketch.setScale((dataGrid.getHeight() - 40) / model.height.getHTotal());
        model.calcWall();
        sketch.sketch();

        txtPa.setText(String.format("%.2f", model.getPa()));
        txtPh.setText(String.format("%.2f", model.getPh()));
        txtQh.setText(String.format("%.2f", model.getQh()));
        txtSh.setText(String.format("%.2f", model.getSh()));
        txtW.setText(String.format("%.2f", model.width.getD()));
        txtMt1.setText(String.format("%.2f", model.getMt1()));
        txtMt2.setText(String.format("%.2f", model.getMt2()));
        txtSmt.setText(String.format("%.2f", model.getSMt()));
        txtKa.setText(String.format("%.2f", model.getKa()));

        txtMrp.setText(String.format("%.2f", model.getMrp()));
        txtMrw.setText(String.format("%.2f", model.getMrw()));
        txtMrs.setText(String.format("%.2f", model.getMrs()));
        txtMrq.setText(String.format("%.2f", model.getMrq()));
        txtMrv.setText(String.format("%.2f", model.getMrv()));

        txtPp.setText(String.format("%.2f", model.getPp()));
        txtVw.setText(String.format("%.2f", model.getVw()));
        txtVs.setText(String.format("%.2f", model.getVs()));
        txtVq.setText(String.format("%.2f", model.getVq()));
        txtPv.setText(String.format("%.2f", model.getPv()));

        txtAS.setText(String.format("%.2f", model.getAS()));
        txtFSS.setText(String.format("%.2f", model.getFss()));
        txtFST.setText(String.format("%.2f", model.getFst()));
        txtE.setText(String.format("%.2f", model.getEffort()));
        txtAngle.setText(String.format("%.2f", model.getTheta()));

        try {
            PrintWall.print(PrintWall.createPrintable(this));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}