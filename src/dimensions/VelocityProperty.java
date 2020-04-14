package dimensions;


public class VelocityProperty extends DimensionProperty {

    private final HeightProperty h;
    private final WidthProperty w;

    public VelocityProperty(HeightProperty h, WidthProperty w) {
        super(0, 0, 0, 0, 0);
        this.h = h;
        this.w = w;
    }

    /**
     * Updates V1, V2, V3, V4
     */


    @Override
    public double getD1() {
        return this.h.getD3() * w.getD();
    }

    @Override
    public double getD2() {
        return 0.5 * (w.getD2() * (h.getHTotal() - h.getD3()));
    }

    @Override
    public double getD3() {
        return w.getD3() * (h.getHTotal() - h.getD3());
    }

    @Override
    public double getD4() {
        return 0.5 * (w.getD5()) * (h.getHTotal() - h.getD3());
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
    @Override
    public void print()
    {
        System.out.print("\n\nV:");
        super.print();
        System.out.printf("\tVtot= %.2f\n", this.getVTotal());
        System.out.printf("\tVs1= %.2f\n", this.getVS1());
        System.out.printf("\tVs2= %.2f\n", this.getVS2());

    }
}
