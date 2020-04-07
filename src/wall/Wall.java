package wall;

import Dimensions.HeightProperty;
import Dimensions.WeightProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Wall {

    /**
     * משקל מרחבי של הקרקע
     * G
     */
    private final DoubleProperty groundWeight;
    // Given:
    // Calculated:

    HeightProperty height;
    WeightProperty weight;

    double Ka = 0.75;
    double Pa = 0;
    /**
     * זוית חיכוך פנימית
     * fi
     */
    private DoubleProperty innerFrictionAngle;
    /**
     * זווית חיכוך קרקע- גב הקיר
     * lambda
     */
    private DoubleProperty groundFrictionAngle;
    /**
     * שיפוע הקרקע טבעית במעלות
     * i
     */
    private DoubleProperty groundAngle;
    /**
     * מאמץ מגע מותר מקסימלי
     */
    private DoubleProperty maxEffort;
    /**
     * שיםוע בסיס הקיר במעלות
     * Theta
     */
    private DoubleProperty baseAngle;
    /**
     * מקדם חיכוך בסיס הקיר-קרקע
     * miu
     */
    private DoubleProperty frictionCoeff;
    /**
     * קוהזיה
     * Co
     */
    private DoubleProperty Cohessia;
    /**
     * עוומס מפורס על הקרקע
     * Q
     */
    private DoubleProperty spatialWeight;
    /**
     * משקל מרחבי של הקיר
     * Gw
     */
    private DoubleProperty wallWeight;
    private DoubleProperty faceSlope;
    private DoubleProperty teta;

    private double beta;

    public Wall(double groundWeight, double innerFrictionAngle, double groundFrictionAngle, double groundAngle, double maxEffort, double baseAngle,
                double frictionCoeff, double cohessia, double spatialWeight, double density) {
        this.groundWeight = new SimpleDoubleProperty(groundWeight);
        this.innerFrictionAngle = new SimpleDoubleProperty(innerFrictionAngle);
        this.groundFrictionAngle = new SimpleDoubleProperty(groundFrictionAngle);
        this.groundAngle = new SimpleDoubleProperty(groundAngle);
        this.maxEffort = new SimpleDoubleProperty(maxEffort);
        this.baseAngle = new SimpleDoubleProperty(baseAngle);
        this.frictionCoeff = new SimpleDoubleProperty(frictionCoeff);
        this.Cohessia = new SimpleDoubleProperty(cohessia);
        this.spatialWeight = new SimpleDoubleProperty(spatialWeight);
        this.wallWeight = new SimpleDoubleProperty(density);
        this.faceSlope = new SimpleDoubleProperty(4);
        this.teta = new SimpleDoubleProperty(10);
        this.height = new HeightProperty(0.8, .4, .4, 0, 1);
        this.weight = new WeightProperty(.1, 0, 0.2, 0, 0, 1.35);

    }

    public Wall() {
        this(2, 30, 20, 0, 35, 0, 0.5, 0, 0.5, 2.4);
    }


    public final DoubleProperty groundWeightProperty() {
        return groundWeight;
    }

    public DoubleProperty innerFrictionAngleProperty() {
        return innerFrictionAngle;
    }

    public DoubleProperty groundFrictionAngleProperty() {
        return groundFrictionAngle;
    }

    public DoubleProperty groundAngleProperty() {
        return groundAngle;
    }

    public double getMaxEffort() {
        return maxEffort.get();
    }

    public DoubleProperty maxEffortProperty() {
        return maxEffort;
    }

    public double getBaseAngle() {
        return baseAngle.get();
    }

    public DoubleProperty baseAngleProperty() {
        return baseAngle;
    }

    public DoubleProperty frictionCoeffProperty() {
        return frictionCoeff;
    }

    public DoubleProperty cohessiaProperty() {
        return Cohessia;
    }

    public DoubleProperty spatialWeightProperty() {
        return spatialWeight;
    }

    public DoubleProperty wallWeightProperty() {
        return wallWeight;
    }

    public double getPh() {
        return (this.getPa() * Math.cos(this.getGroundFrictionAngle() + this.teta.get()));
    }

    public double getQh() {
        return spatialWeight.get() * height.getHTotal() * Ka;
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

    double getSh() {
        return getPh() + getQh();
    }

    double getSMt() {
        return getMt1() + getMt2();
    }


    public double Qh() {
        return spatialWeight.get() * height.getD() * Ka;
    }

    public double getGroundWeight() {
        return groundWeight.get();
    }

    public final void setGroundWeight(double groundWeight) {
        this.groundWeight.set(groundWeight);
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

    public final void calc() {
        try {
            this.weight.setD2((this.height.getHTotal() - this.height.getD2()) * this.getFaceSlopePercent());
           // this.weight.setD5(this.weight.getD() - this.weight.getD1() - this.weight.getD2() - this.weight.getD3() - this.weight.getD4());
            beta = 180 - Math.toDegrees(Math.atan((this.height.getHTotal() - this.height.getD3()) / (this.weight.getD5() + this.weight.getD4())));

            double a, b, c, d;
            a = (cosec(Math.toRadians(beta)) * Math.sin(Math.toRadians(beta - getInnerFrictionAngle())));
            b = Math.sin(Math.toRadians(beta + getGroundFrictionAngle()));
            c = Math.sin(Math.toRadians(getInnerFrictionAngle() + getGroundFrictionAngle())) * Math.sin(Math.toRadians(getInnerFrictionAngle() - getGroundAngle()));
            d = Math.sin(Math.toRadians(beta - getGroundAngle()));
            this.Ka = a / (b + Math.sqrt(c / d));
            this.Pa = (groundWeight.get() * getKa() * Math.pow(this.height.getHTotal(), 2)) / 2;
        } catch (Exception e) {
        }

    }
//public double calcKa(doubkle)
//{
//
//}
    private double getFaceSlopePercent() {
        return this.getFaceSlope() / 100;
    }

    private double cosec(double v) {
        return 1 / Math.sin(v);
    }

    public double getInnerFrictionAngle() {
        return innerFrictionAngle.get();
    }

    public void setInnerFrictionAngle(float innerFrictionAngle) {
        this.innerFrictionAngle.set(innerFrictionAngle);
    }

    public double getGroundFrictionAngle() {
        return groundFrictionAngle.get();
    }

    public void setGroundFrictionAngle(float groundFrictionAngle) {
        this.groundFrictionAngle.set(groundFrictionAngle);
    }

    public double getGroundAngle() {
        return groundAngle.get();
    }

    public void setGroundAngle(float groundAngle) {
        this.groundAngle.set(groundAngle);
    }

    public double getFrictionCoeff() {
        return frictionCoeff.get();
    }

    public void setFrictionCoeff(float frictionCoeff) {
        this.frictionCoeff.set(frictionCoeff);
    }

    public double getCohessia() {
        return Cohessia.get();
    }

    public void setCohessia(double cohessia) {
        Cohessia.set(cohessia);
    }

    public double getSpatialWeight() {
        return spatialWeight.get();
    }

    public void setSpatialWeight(float spatialWeight) {
        this.spatialWeight.set(spatialWeight);
    }

    public double getWallWeight() {
        return wallWeight.get();
    }

    public void setWallWeight(float wallWeight) {
        this.wallWeight.set(wallWeight);
    }
}
