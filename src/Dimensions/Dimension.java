package Dimensions;

public abstract class Dimension {
    double d1 = 0;
    double d2 = 0;
    double d3 = 0;
    double d4 = 0;
    double d = 0;

    public Dimension(double d1, double d2, double d3, double d4, double d) {
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.d = d;
    }

    public void setD1(double d1) {
        this.d1 = d1;
    }

    public void setD2(double d2) {
        this.d2 = d2;
    }

    public void setD3(double d3) {
        this.d3 = d3;
    }

    public void setD4(double d4) {
        this.d4 = d4;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getD1() {
        return d1;
    }

    public double getD2() {
        return d2;
    }

    public double getD3() {
        return d3;
    }

    public double getD4() {
        return d4;
    }

    public double getD() {
        return d;
    }
}
