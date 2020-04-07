package Dimensions;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class WeightProperty extends DimensionProperty {
    DoubleProperty d5;
    Weight w;
    public WeightProperty(double d1, double d2, double d3, double d4, double d5, double d) {
        super(d1, d2, d3, d4, d);
        this.d5 = new SimpleDoubleProperty(d5);


    }
    public double getD5() {
        return getD()-getD1()-getD2()-getD3()-getD4();
    }





}
