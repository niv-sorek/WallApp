package wall;

import Dimensions.HeightProperty;
import Dimensions.VelocityProperty;
import Dimensions.WeightProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Wall {


    // Given:
    // Calculated:


    final HeightProperty height;
    final WeightProperty weight;
    final VelocityProperty velocity;
    /**
     * משקל מרחבי של הקרקע
     * G
     */
    private final DoubleProperty gamma;
    /**
     * זוית חיכוך פנימית
     * fi
     */
    private final DoubleProperty fi;
    /**
     * זווית חיכוך קרקע- גב הקיר
     * lambda
     */
    private final DoubleProperty lambda;
    /**
     * שיפוע הקרקע טבעית במעלות
     * i
     */
    private final DoubleProperty i;
    /**
     * מאמץ מגע מותר מקסימלי
     */
    private final DoubleProperty maxEffort;
    /**
     * שיםוע בסיס הקיר במעלות
     * Theta
     */
    private final DoubleProperty theta;
    /**
     * מקדם חיכוך בסיס הקיר-קרקע
     * miu
     */
    private final DoubleProperty miu;
    /**
     * קוהזיה
     * Co
     */
    private final DoubleProperty Co;
    /**
     * עוומס מפורס על הקרקע
     * Q
     */
    private final DoubleProperty q;
    /**
     * משקל מרחבי של הקיר
     * Gw
     */
    private final DoubleProperty Gw;
    private final DoubleProperty faceSlope;
    private double beta;

    // Constructors:
    public Wall(double gamma, double fi, double lambda, double i, double maxEffort, double theta,
                double miu, double co, double q, double gw) {
        this.gamma = new SimpleDoubleProperty(gamma);
        this.fi = new SimpleDoubleProperty(fi);
        this.lambda = new SimpleDoubleProperty(lambda);
        this.i = new SimpleDoubleProperty(i);
        this.maxEffort = new SimpleDoubleProperty(maxEffort);
        this.theta = new SimpleDoubleProperty(theta);
        this.miu = new SimpleDoubleProperty(miu);
        this.Co = new SimpleDoubleProperty(co);
        this.q = new SimpleDoubleProperty(q);
        this.Gw = new SimpleDoubleProperty(gw);
        this.faceSlope = new SimpleDoubleProperty(4);
        this.height = new HeightProperty(0.8, .4, .4, 0, 1);
        this.weight = new WeightProperty(.1, 0, 0.2, 0, 0, 1.35);
        this.velocity = new VelocityProperty(this.height, this.weight);

    }

    public Wall() {
        this(2, 30, 20, 0, 35, 0, 0.5, 0, 0.5, 2.4);
    }

    //Getters And Setters:
    public final DoubleProperty gammaProperty() {
        return gamma;
    }

    public DoubleProperty fiProperty() {
        return fi;
    }

    public DoubleProperty lambdaProperty() {
        return lambda;
    }

    public DoubleProperty iProperty() {
        return i;
    }

    public double getMaxEffort() {
        return maxEffort.get();
    }

    public DoubleProperty maxEffortProperty() {
        return maxEffort;
    }

    public double getTheta() {
        return theta.get();
    }

    public DoubleProperty thetaProperty() {
        return theta;
    }

    public DoubleProperty miuProperty() {
        return miu;
    }

    public DoubleProperty coProperty() {
        return Co;
    }

    public DoubleProperty qProperty() {
        return q;
    }

    public DoubleProperty gwProperty() {
        return Gw;
    }

    public double getGamma() {
        return gamma.get();
    }

    public final void setGamma(double gamma) {
        this.gamma.set(gamma);
    }

    public double getFaceSlope() {
        return faceSlope.get();
    }

    public void setFaceSlope(double faceSlope) {
        this.faceSlope.set(faceSlope);
    }

    public DoubleProperty faceSlopeProperty() {
        return faceSlope;
    }

    public double getPa() {
        return (getGamma() * getKa() * Math.pow(this.height.getHTotal(), 2)) / 2;
    }

    private double getFaceSlopePercent() {
        return this.getFaceSlope() / 100;
    }

    public double getFi() {
        return fi.get();
    }

    public void setFi(float fi) {
        this.fi.set(fi);
    }

    public double getLambda() {
        return lambda.get();
    }

    public void setLambda(float lambda) {
        this.lambda.set(lambda);
    }

    public double getI() {
        return i.get();
    }

    public void setI(float i) {
        this.i.set(i);
    }

    public double getMiu() {
        return miu.get();
    }

    public void setMiu(double miu) {
        this.miu.set(miu);
    }

    public double getCo() {
        return Co.get();
    }

    public void setCo(double co) {
        Co.set(co);
    }

    public double getQ() {
        return q.get();
    }

    public void setQ(double q) {
        this.q.set(q);
    }

    public double getGw() {
        return Gw.get();
    }

    public void setGw(double gw) {
        this.Gw.set(gw);
    }

    //--------
    // Calculation Functions
    public double getPh() {
        return (this.getPa() * Math.cos(Math.toRadians(90 + this.getLambda() - getR())));
    }

    public double getQh() {
        return q.get() * height.getHTotal() * getKa();
    }

    double getSh() {
        return getPh() + getQh();
    }

    double getMt1() {
        return getPh() * height.getD1() / 3;
    }

    double getMt2() {
        return getQh() * height.getD1() / 2;
    }

    double getSMt() {
        return getMt1() + getMt2();
    }

    double getPv() {
        return getPa() * Math.sin(Math.toRadians(getLambda() + theta.get()));
    }

    double getVw() {
        return this.getGamma() * this.velocity.getVTotal();
    }

    double getVs() {
        return (this.velocity.getVS1() + this.velocity.getVS2()) * getGamma();
    }

    double getVq() {
        return getQ() * (this.weight.getD4() + this.weight.getD5());
    }

    double getPp() {
        return getGamma() * Math.pow(this.height.getD1(), 2) / (getKa() * 2);
    }

    double getSv() {
        return getPv() + getVw() + getVs() + getVq() + getPp();
    }

    double getMrv() {
        return this.getPv() * (weight.getD1() + weight.getD2() + weight.getD3() + 0.5 * (weight.getD4() + weight.getD5()));
    }

    double getCenterOfMass() {
        double a = velocity.getD1() * weight.getD1() * 0.5;
        double b = velocity.getD2() * (weight.getD1() + weight.getD2() * 2 / 3);
        double c = weight.getD3() * (weight.getD1() + weight.getD2() + weight.getD3() / 2);
        double d = weight.getD4() * (weight.getD1() + weight.getD2() + weight.getD3() + weight.getD5() / 3);
        double sum = a + b + c + d;
        return sum / this.velocity.getVTotal();
    }

    double getMrw() {
        return getVw() * getCenterOfMass();
    }

    double getMrs() {
        double a = velocity.getVS1() * (weight.getD1() + weight.getD2() + weight.getD3() + weight.getD5() * 2 / 3);
        double b = velocity.getVS2() * (weight.getD() - (weight.getD4() + weight.getD5()) / 2);
        return a + b;
    }

    double getMrq() {
        return getVq() * (weight.getD() - (weight.getD5() + weight.getD4()) / 2);
    }

    double getMrp() {
        return getPp() * height.getD3() / 3;
    }

    double getSmr() {
        return getMrv() + getMrw() + getMrs() + getMrq() + getMrp();
    }

    double getFst() {
        return getSmr() / getSMt();
    }

    // // TODO: 09/04/2020 complete func with sin cos
    double getFss() {
        return getSv() * getMiu() / getSMt();
    }

    //TODO Complete function & new Formula
    public double getKa() {
        double a, b, c, d;
        double R = 180-getR();
        System.out.println("R= " + R);

        a = sin2(Math.toRadians(R + getFi()));
        b = sin2(Math.toRadians(R)) * sin2(Math.toRadians(R - getLambda()));
        c = Math.sin(Math.toRadians(getFi() + getLambda())) * Math.sin(Math.toRadians(getFi() - getI()));
        d = Math.sin(Math.toRadians(R - getLambda())) * Math.sin(Math.toRadians(R + getI()));
        return a / (b * Math.pow((1 + Math.sqrt(c / d)), 2));
    }

    private double sin2(double radians) {
        return Math.pow(Math.sin(radians), 2);
    }
   private double cos2(double radians) {
        return Math.pow(Math.cos(radians), 2);
    }

    private double getR() {
        weight.update(height, getFaceSlopePercent());
        System.out.printf("atan((%.2f - %.2f) / (%.2f + %.2f))", this.height.getHTotal(), this.height.getD3(), this.weight.getD5(), this.weight.getD4());
        return Math.toDegrees(Math.atan((this.height.getHTotal() - this.height.getD3()) / (this.weight.getD5() + this.weight.getD4())));
    }


    public void increaseW(double v) {
        this.weight.setD(this.weight.getD() + v);
        this.weight.update(height, this.getFaceSlopePercent());
        //this.height.update();
        this.velocity.update();
    }

    public void iterate(double incVal, double minFss, double minFst) {
        while ((getFss() < minFss || getFst() < minFst) && weight.getD() < 5)
            increaseW(incVal);
    }

    /**
     * calculate all relevant information about the current wall
     */
    public final void calcWall() {
        try {
            //this.height.update();
            this.weight.update(height, this.getFaceSlopePercent());
            this.velocity.update();
            System.out.println("VTotal = " + velocity.getVTotal());
            System.out.println("Ka = " + getKa());

        } catch (Exception ignored) {
        }

    }

    public void iterate() {
        this.iterate(0.5, 1.5, 3);
    }
}
