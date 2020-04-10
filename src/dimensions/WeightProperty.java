package dimensions;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class WeightProperty extends DimensionProperty {

    final DoubleProperty d5;
    HeightProperty h;
    double slope;

    public WeightProperty(double d1, double d2, double d3, double d4, double d5, double d ,HeightProperty h, double slope) {
        super(d1, d2, d3, d4, d);
        this.d5 = new SimpleDoubleProperty(d5);
        this.update(h, slope);
    }


    public void update(HeightProperty h, double slope) {
        this.slope = slope;
        this.h = h;
        this.setD2(this.getD2());
    }


    @Override
    public double getD2() {
        return (this.h.getHTotal() - this.h.getD2()) * this.slope;
    }


    public double getD5() {
        return this.getD() - this.getD1() - this.getD2() - this.getD3() - this.getD4();
    }


    public void setD5(double d5) {
        this.d5.set(d5);
    }

}
