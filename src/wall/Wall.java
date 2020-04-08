package wall;

import Dimensions.HeightProperty;
import Dimensions.VelocityProperty;
import Dimensions.WeightProperty;
import Utils.WallUtils;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Wall {


    // Given:
    // Calculated:


    /**
     * משקל מרחבי של הקרקע
     * G
     */
    private final DoubleProperty gamma;
    HeightProperty height;
    WeightProperty weight;
    VelocityProperty velocity;
    double Ka = 0.75;
    double Pa = 0;
    /**
     * זוית חיכוך פנימית
     * fi
     */
    private DoubleProperty fi;
    /**
     * זווית חיכוך קרקע- גב הקיר
     * lambda
     */
    private DoubleProperty lanbda;
    /**
     * שיפוע הקרקע טבעית במעלות
     * i
     */
    private DoubleProperty i;
    /**
     * מאמץ מגע מותר מקסימלי
     */
    private DoubleProperty maxEffort;
    /**
     * שיםוע בסיס הקיר במעלות
     * Theta
     */
    private DoubleProperty theta;
    /**
     * מקדם חיכוך בסיס הקיר-קרקע
     * miu
     */
    private DoubleProperty miu;
    /**
     * קוהזיה
     * Co
     */
    private DoubleProperty Co;
    /**
     * עוומס מפורס על הקרקע
     * Q
     */
    private DoubleProperty q;
    /**
     * משקל מרחבי של הקיר
     * Gw
     */
    private DoubleProperty Gw;
    private DoubleProperty faceSlope;
    private DoubleProperty teta;

    private double beta;

    // Constructors:
    public Wall(double gamma, double fi, double lanbda, double i, double maxEffort, double theta,
                double miu, double co, double q, double gw) {
        this.gamma = new SimpleDoubleProperty(gamma);
        this.fi = new SimpleDoubleProperty(fi);
        this.lanbda = new SimpleDoubleProperty(lanbda);
        this.i = new SimpleDoubleProperty(i);
        this.maxEffort = new SimpleDoubleProperty(maxEffort);
        this.theta = new SimpleDoubleProperty(theta);
        this.miu = new SimpleDoubleProperty(miu);
        this.Co = new SimpleDoubleProperty(co);
        this.q = new SimpleDoubleProperty(q);
        this.Gw = new SimpleDoubleProperty(gw);
        this.faceSlope = new SimpleDoubleProperty(4);
        this.teta = new SimpleDoubleProperty(10);
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

    public DoubleProperty lanbdaProperty() {
        return lanbda;
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
        return Pa;
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

    public double getLanbda() {
        return lanbda.get();
    }

    public void setLanbda(float lanbda) {
        this.lanbda.set(lanbda);
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

    public void setMiu(float miu) {
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

    public void setQ(float q) {
        this.q.set(q);
    }

    public double getGw() {
        return Gw.get();
    }

    public void setGw(float gw) {
        this.Gw.set(gw);
    }

    //--------
    // Calculation Faunctions
    public double getPh() {
        return (this.getPa() * Math.cos(this.getLanbda() + this.teta.get()));
    }

    public double getQh() {
        return q.get() * height.getHTotal() * Ka;
    }

    double getSh() {
        return getPh() + getQh();
    }

    public double getKa() {
        return Ka;
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
        return getPa() * Math.sin(this.getLanbda() + this.teta.get());
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

    //TODO complete function
    // - understand A.S, F ...
    double getFss() {
        return 0;
    }

    //TODO Complete function & new Formula
    public double calcKa() {
        double a, b, c, d;
        beta = 180 - Math.toDegrees(Math.atan((this.height.getHTotal() - this.height.getD3()) / (this.weight.getD5() + this.weight.getD4())));
        a = (WallUtils.cosec(Math.toRadians(beta)) * Math.sin(Math.toRadians(beta - getFi())));
        b = Math.sin(Math.toRadians(beta + getLanbda()));
        c = Math.sin(Math.toRadians(getFi() + getLanbda())) * Math.sin(Math.toRadians(getFi() - getI()));
        d = Math.sin(Math.toRadians(beta - getI()));
        testPrintKa(a, c, d, b);
        return a / (b + Math.sqrt(c / d));
    }

    private void testPrintKa(double a, double c, double d, double b) {
        System.out.println("\n\nbeta=" + beta + "\tfi= " + getFi() + "\t\n");
        System.out.printf("a = (cosec(toRadians(%.2f)) * sin(toRadians(%.2f - %.2f)))\n", beta, beta, getFi());
        System.out.printf("b = sin(toRadians(%.2f + %.2f))\n", beta, getFi());
        System.out.printf("c = sin(toRadians(%.2f +%.2f)) * sin(toRadians(%.2f - %.2f))\n", getFi(), getLanbda(), getFi(), getLanbda());
        System.out.printf("d = sin(toRadians(%.2f - %.2f))\n\n", beta, getLanbda());

        System.out.println("a / (b + Math.sqrt(c / d)\n");
        System.out.printf("%.2f / (%.2f + Math.sqrt(%.2f / %.2f)\n", a, b, c, d);
    }


    public void increaseW(double v) {
        this.weight.setD(this.weight.getD() + v);
    }

    public void iterate(double incVal, double minFss, double minFst, double fss, double fst) {
        while (fss < minFss || fst < minFst) increaseW(incVal);
    }

    /**
     * calculate all relevant information about the current wall
     */
    public final void calcWall() {
        try {
            this.height.update(this.getFaceSlopePercent());
            this.weight.update();
            this.velocity.update();
            System.out.println("VTotal = " + velocity.getVTotal());
            this.Ka = calcKa();
            this.Pa = (gamma.get() * getKa() * Math.pow(this.height.getHTotal(), 2)) / 2;
        } catch (Exception ignored) {
        }

    }
}
