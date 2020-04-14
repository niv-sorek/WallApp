package dimensions;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class WidthProperty extends DimensionProperty {

    final DoubleProperty d5;

    HeightProperty h;
    double slope;

    public WidthProperty(double d1, double d2, double d3, double d4, double d, HeightProperty h, double slope) {
        super(d1, d2, d3, d4, d);
        this.d5 = new SimpleDoubleProperty(0);
        this.update(h, slope);
    }

    public void update(HeightProperty h, double slope) {
        this.slope = slope;
        this.h = h;
    }

    @Override
    public double getD2() {
        return (this.h.getHTotal() - this.h.getD2()) * this.slope;
    }

    public double getD5() {
        return this.d5.get();
    }

    public void setD5(double d5) {
        this.d5.set(d5);
    }

    @Override
    public double getD() {
        return this.getD1() + getD2() + getD3() + getD4() + getD5();
    }

    @Override
    public void setD(double d) {
        super.setD(d);
    }

    @Override
    public void print() {
        System.out.print("\n\nW:");
        super.print();
        System.out.printf("\t5= %.2f\n", this.getD5());
    }

    @Override
    public void bind(TextField d1, TextField d2, TextField d3, TextField d4, TextField d) {
        super.bind(d1, d2, d3, d4, d);
        d.textProperty().unbind();
       // d.textProperty().bindBidirectional(this.d, new NumberStringConverter());
    }
}
