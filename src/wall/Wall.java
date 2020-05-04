package wall;

import dimensions.HeightProperty;
import dimensions.VelocityProperty;
import dimensions.WidthProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.awt.*;

public class Wall {


    // Given:
    // Calculated:


    final HeightProperty height;
    final WidthProperty width;
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
    private final DoubleProperty sigma;
    /**
     * שיםוע בסיס הקיר במעלות
     * alpha
     */
    private final DoubleProperty alpha;
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
    private final BooleanProperty isPP;
    private String Name;

    // Constructors:
    public Wall(double gamma, double fi, double lambda, double i, double sigma, double alpha,
                double miu, double co, double q, double gw) {
        this.gamma = new SimpleDoubleProperty(gamma);
        this.fi = new SimpleDoubleProperty(fi);
        this.lambda = new SimpleDoubleProperty(lambda);
        this.i = new SimpleDoubleProperty(i);
        this.sigma = new SimpleDoubleProperty(sigma);
        this.alpha = new SimpleDoubleProperty(alpha);
        this.miu = new SimpleDoubleProperty(miu);
        this.Co = new SimpleDoubleProperty(co);
        this.q = new SimpleDoubleProperty(q);
        this.Gw = new SimpleDoubleProperty(gw);
        this.faceSlope = new SimpleDoubleProperty(4);
        this.height = new HeightProperty(0.8, .4, .4, 0, 1);
        this.width = new WidthProperty(.1, 0, 0.2, 0, 0.3, height, getFaceSlopePercent());
        this.velocity = new VelocityProperty(this.height, this.width);
        this.isPP = new SimpleBooleanProperty(false);
    }

    public Wall() {
        this(2, 30, 20, 0, 35, 0, 0.5, 0, 0.5, 2.4);
    }

    static void drawStringRTL(Graphics g, String str, int x, int y) {
        g.drawString(str, x - g.getFontMetrics().stringWidth(str), y);
    }

    static void drawStringCenterRTL(Graphics g, String str, int width, int y) {
        int x = width / 2 - g.getFontMetrics().stringWidth(str) / 2;
        g.drawString(str, x, y);
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

    public double getSigma() {
        return sigma.get();
    }

    public DoubleProperty sigmaProperty() {
        return sigma;
    }

    public DoubleProperty alphaProperty() {
        return alpha;
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

    public BooleanProperty isPPProperty() {
        return isPP;
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
        return (this.getPa() * cosd(90 + this.getLambda() - getTheta()));
    }

    public double getQh() {
        return q.get() * height.getHTotal() * getKa();
    }

    double getSh() {
        return getPh() + getQh();
    }

    double getMt1() {
        return getPh() * height.getHTotal() / 3;
    }

    double getMt2() {
        return getQh() * height.getHTotal() / 2;
    }

    double getSMt() {
        return getMt1() + getMt2();
    }

    double getPv() {
        return getPa() * sind(90 + this.getLambda() - getTheta());
    }

    double getVw() {

        return velocity.getVTotal() * getGw();
    }

    double getKp() {

        return 1 / (Math.pow(tand(45 - 0.5 * getFi()), 2));
    }

    double getVs() {
        return (this.velocity.getVS1() + this.velocity.getVS2()) * getGamma();
    }

    double getVq() {
        return getQ() * (this.width.getD4() + this.width.getD5());
    }

    double getPp() {
        return getGamma() * Math.pow(this.height.getD1(), 2) * getKp() / 2;
    }

    double getSv() {
        return getPv() + getVw() + getVs() + getVq() + getPp();
    }

    double getMrv() {
        return this.getPv() * (width.getD() - width.getD4() - width.getD5() / 3);
    }

    double getCenterOfMass() {

        double a = velocity.getD1() * width.getD() * 0.5;
        double b = velocity.getD2() * (width.getD1() + width.getD2() * 2 / 3);
        double c = velocity.getD3() * (width.getD1() + width.getD2() + width.getD3() / 2);
        double d = velocity.getD4() * (width.getD1() + width.getD2() + width.getD3() + width.getD5() / 3);
        // System.out.printf("a= %.2f\tb= %.2f\tc= %.2f\td=%.2f\n CenterOfMass=(a+b+c+d)/%.2f=%.2f\n", a, b, c, d, velocity.getVTotal(), (a + b + c + d) / this.velocity.getVTotal());
        return (a + b + c + d) / this.velocity.getVTotal();
    }

    double getMrw() {
        return getVw() * getCenterOfMass();
    }

    double getMrs() {
        double a = velocity.getVS1() * (width.getD1() + width.getD2() + width.getD3() + width.getD5() * 2 / 3);
        double b = velocity.getVS2() * (width.getD() - (width.getD4() + width.getD5()) / 2);
        return a + b;
    }

    double getMrq() {
        return getVq() * (width.getD() - (width.getD5() + width.getD4()) / 2);
    }

    double getMrp() {
        return getPp() * height.getD1() / 3;
    }

    double getSmr() {
        return getMrv() + getMrw() + getMrs() + getMrq() + getMrp();
    }

    double getFst() {
        return getSmr() / getSMt();
    }
    //TODO Complete function & new Formula

    double getFss() {
        return getAS() / getSh();
    }

    double getFf() {
        return getMiu() * (getSv() * cosd(getAlpha()) + getSh() * sind(getAlpha()));
    }

    public double getAS() {
        return this.getFf() + (isPP.get() ? getPp() : 0);
    }

    private double getAlpha() {
        return this.alpha.get();
    }

    public double getKa() {
        double a, b, c, d;
        double theta = 90 - getTheta(); //*** I removed the 90-theta
//        System.out.println("theta= " + theta);

      /*  a = sin2(Math.toRadians(theta + getFi())) * Math.cos(Math.toRadians(getLambda()));
        b = Math.sin(Math.toRadians(theta)) * Math.sin(Math.toRadians(theta - getLambda()));
        c = Math.sin(Math.toRadians(getFi() + getLambda())) * Math.sin(Math.toRadians(getFi() - getI()));
        d = Math.sin(Math.toRadians(theta - getLambda())) * Math.sin(Math.toRadians(theta + getI()));*/
/*
        a = sin2d(theta + getFi());
        b = sin2d(theta) * sin2d(theta - getLambda());
        c = sind(getFi() + getLambda()) * sind(getFi() - getI());
        d = sind(theta - getLambda()) * sind(theta + getI());
*/
        a = cos2d(getFi() - theta);
        b = cos2d(theta) * cosd(theta + getLambda());
        c = sind(getFi() + getLambda()) * sind(getFi() - getI());
        d = cosd(theta + getLambda()) * cosd(theta - getI());


        return a / (b * Math.pow((1 + Math.sqrt(c / d)), 2));
//        return (a / b) * cosd(theta);
    }

    private double sind(double deg) {
        return Math.sin(Math.toRadians(deg));
    }

    private double cosd(double deg) {
        return Math.cos(Math.toRadians(deg));
    }

    private double tand(double deg) {
        return Math.tan(Math.toRadians(deg));
    }

    private double sin2d(double deg) {
        return Math.pow(sind(deg), 2);
    }

    private double cos2d(double deg) {
        return Math.pow(cosd(deg), 2);
    }

    public double getTheta() {
        width.update(height, getFaceSlopePercent());
        // System.out.printf("atan((%.2f - %.2f) / (%.2f ))\n", this.height.getHTotal(), this.height.getD3(), this.width.getD5());
        return Math.toDegrees(Math.atan((this.height.getHTotal() - this.height.getD3()) / (this.width.getD5() + this.width.getD4())));
    }

    public void increaseW(double v) {
        this.width.setD5(this.width.getD5() + v);
        this.width.update(height, this.getFaceSlopePercent());
        //this.height.update();

    }

    double getEffort() {
        return 6 * getFst() * getSMt() / Math.pow(width.getD(), 2);
    }

    /**
     * calculate all relevant information about the current wall
     */
    public final void calcWall() {
        try {
            //this.height.update();
            this.width.update(height, this.getFaceSlopePercent());

            // print();
        } catch (Exception ignored) {
        }
    }

    public void iterate() {
        this.iterate(0.05, 1.5, 1.5);
    }

    public void iterate(double incVal, double minFss, double minFst) {
        int count = 0;
        this.width.setD5(0);
        while ((getFss() < minFss || getFst() < minFst || getEffort() > getSigma()) && count < 1000) {
            increaseW(incVal);
            count++;

            System.out.println("iterates: " + count);
            // print();
        }

    }


}


