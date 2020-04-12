package dimensions;


public class HeightProperty extends DimensionProperty {


    public HeightProperty(double d1, double d2, double d3, double d4, double d) {
        super(d1, d2, d3, d4, d);
    }

    public double getHTotal() {
        return this.getD() + this.getD1();
    }
    @Override
    public void print()
    {
        System.out.print("\n\nH:");
        super.print();
        System.out.printf("\tHtot= %.2f", this.getHTotal());
    }

}
