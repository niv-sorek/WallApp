package Dimensions;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class WeightProperty extends DimensionProperty {


    final DoubleProperty d5;

    public WeightProperty(double d1, double d2, double d3, double d4, double d5, double d) {
        super(d1, d2, d3, d4, d);
        this.d5 = new SimpleDoubleProperty(d5);
    }

    /**
     * calculate the value of W2 = H-(H2*%_Slope)
     * Update the W5 = W - W1 - W2 -W3 - W4
     */
    public void update(HeightProperty h, double slope) {
        this.setD2((h.getHTotal() - h.getD2()) * slope);
        this.setD5(this.getD() - this.getD1() - this.getD2() - this.getD3() - this.getD4());
    }

    public double getD5() {
        return d5.get();
    }

    public void setD5(double d5) {
        this.d5.set(d5);
    }

}
