package Dimensions;


public class VelocityProperty extends DimensionProperty {

    private HeightProperty h;
    private WeightProperty w;

    public VelocityProperty(double d1, double d2, double d3, double d4, double d) {
        super(d1, d2, d3, d4, d);
    }

    public VelocityProperty(HeightProperty h, WeightProperty w) {
        super(0, 0, 0, 0, 0);
        this.h = h;
        this.w = w;
        update();
    }

    /**
     * Updates V1, V2, V3, V4
     */
    public void update() {
        this.setD1(h.getD3() * w.getD());
        this.setD2(0.5 * (w.getD2() * (h.getHTotal() - h.getD3())));
        this.setD3(w.getD3() * (h.getHTotal() - h.getD3()));
        this.setD4((w.getD4() - (w.getD1() + w.getD2() + w.getD3() + w.getD4())) * (h.getHTotal() - h.getD3()));
    }

    public double getVTotal() {
        return this.getD1() + this.getD2() + this.getD3() + this.getD4();
    }

    /**
     * Calculate Vs2 by H & W
     *
     * @return Vs1 = W4*(H-H3)
     */
    public double getVS2() {
        return w.getD4() * (h.getHTotal() - h.getD3());
    }

    /**
     * Calculate Vs1 by H & W
     *
     * @return Vs2 = W5*(H-H3)/2
     */
    public double getVS1() {
        return 0.5 * w.getD5() * (h.getHTotal() - h.getD3());
    }
}
