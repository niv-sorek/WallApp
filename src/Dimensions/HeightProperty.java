package Dimensions;


public class HeightProperty extends DimensionProperty {


    public HeightProperty(double d1, double d2, double d3, double d4, double d) {
        super(d1, d2, d3, d4, d);
    }

    public double getHTotal() {
        return this.getD() + this.getD1();
    }

    /**
     * calculate the value of W2 = H-(H2*%_Slope)
     * @param faceSlopePercent שיפוע חזית הקיר באחוזים
     */
    public void update(double faceSlopePercent){
        this.setD2((this.getHTotal() - this.getD2()) * faceSlopePercent);
    }
}
