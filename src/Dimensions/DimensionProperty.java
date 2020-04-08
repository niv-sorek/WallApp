package Dimensions;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public abstract class DimensionProperty {
    final DoubleProperty d1;
    final DoubleProperty d2;
    final DoubleProperty d3;
    final DoubleProperty d4;
    final DoubleProperty d;

    public DimensionProperty(DoubleProperty d1, DoubleProperty d2, DoubleProperty d3, DoubleProperty d4, DoubleProperty d) {
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.d = d;
    }

    public DimensionProperty(double d1, double d2, double d3, double d4, double d) {
        this(new SimpleDoubleProperty(d1), new SimpleDoubleProperty(d2), new SimpleDoubleProperty(d3), new SimpleDoubleProperty(d4), new SimpleDoubleProperty(d));
    }

    public double getD1() {
        return d1.get();
    }

    public void setD1(double d1) {
        this.d1.set(d1);
    }

    public double getD2() {
        return d2.get();
    }

    public void setD2(double d2) {
        this.d2.set(d2);
    }

    public double getD3() {
        return d3.get();
    }

    public void setD3(double d3) {
        this.d3.set(d3);
    }

    public double getD4() {
        return d4.get();
    }

    public void setD4(double d4) {
        this.d4.set(d4);
    }

    public double getD() {
        return d.get();
    }

    public void setD(double d) {
        this.d.set(d);
    }

    public void bind(TextField d1, TextField d2, TextField d3, TextField d4, TextField d) {
        d1.textProperty().bindBidirectional(this.d1, new NumberStringConverter());
        d2.textProperty().bindBidirectional(this.d2, new NumberStringConverter());
        d3.textProperty().bindBidirectional(this.d3, new NumberStringConverter());
        d4.textProperty().bindBidirectional(this.d4, new NumberStringConverter());
        d.textProperty().bindBidirectional(this.d, new NumberStringConverter());
    }

}
