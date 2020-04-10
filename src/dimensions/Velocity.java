package dimensions;

@SuppressWarnings("unused")
public class Velocity extends Dimension {
    private double V1, V2, V3, V4;
    private Height height;
    private Weight weight;

//    public Velocity(Height h, Weight w) {
//        height = h;
//        weight = w;
//        update();
//    }

    public Velocity(double d1, double d2, double d3, double d4, double d) {
        super(d1, d2, d3, d4, d);
    }

    @SuppressWarnings("EmptyMethod")
    private void update() {
//        V1 = height.getH3() * weight.getTotal();
//        V2 = (weight.getW2() * (height.getTotal() - height.getH3())) / 2;
//        V3 = weight.getW3() * (height.getTotal() - height.getH3());
//        V4 = ((weight.getW4() - (weight.getW1() + weight.getW2() + weight.getW3() + weight.getW4())) *
//                (height.getTotal() - height.getH3()));
    }

    public double getV1() {
        return V1;
    }

    public double getV2() {
        return V2;
    }

    public double getV3() {
        return V3;
    }

    public double getV4() {
        return V4;
    }


    /**
     * @return The Center of gravity of the wall calculated by the Height, Weight, and Velocity
     */
//
}
