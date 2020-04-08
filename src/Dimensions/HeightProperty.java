package Dimensions;


public class HeightProperty extends DimensionProperty {


    public HeightProperty(double d1, double d2, double d3, double d4, double d) {
        super(d1, d2, d3, d4, d);
    }

    public double getHTotal() {
        return this.getD() + this.getD1();
    }


}
