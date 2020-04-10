package wall;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class WallGraphics extends Canvas {
    private static final double MARGIN = 20;
    Double[] p0 = {0.0, 0.0};
    Polygon p = new Polygon();
    double scale = 1;
    Wall wall;
    GraphicsContext content;

    public WallGraphics(Wall wall) {
        this.wall = wall;
        this.scale = 150;
        content = this.getGraphicsContext2D();
    }

    public void sketch() {
        p0 = new Double[]{(this.getWidth() - wall.weight.getD() * scale) / 2, this.getHeight() - MARGIN};
        content.clearRect(0, 0, this.getWidth(), this.getHeight());
        content.setStroke(Color.BLACK);
        content.setFill(Color.GRAY);
        content.strokePolygon(new double[]{
                        p0[0],
                        getP1()[0],
                        getP2()[0],
                        getP3()[0],
                        getP4()[0],
                        getP5()[0],
                        getP6()[0],
                        getP7()[0]},
                new double[]{
                        p0[1],
                        getP1()[1],
                        getP2()[1],
                        getP3()[1],
                        getP4()[1],
                        getP5()[1],
                        getP6()[1],
                        getP7()[1]}, 8);

    }

    private Double[] getP1() {
        return new Double[]{p0[0], p0[1] - wall.height.getD3() * scale};
    }

    private Double[] getP2() {
        Double[] prev = getP1();
        return new Double[]{prev[0] + wall.weight.getD1() * scale, prev[1]};
    }

    private Double[] getP3() {
        Double[] prev = getP2();
        return new Double[]{prev[0] + wall.weight.getD2() * scale, prev[1] - (wall.height.getHTotal() - wall.height.getD3()) * scale};
    }

    private Double[] getP4() {
        Double[] prev = getP3();
        return new Double[]{prev[0] + wall.weight.getD3() * scale, prev[1]};
    }

    private Double[] getP5() {
        Double[] prev = getP4();
        return new Double[]{prev[0] + wall.weight.getD5() * scale, prev[1] + (wall.height.getHTotal() - wall.height.getD3()) * scale};
    }

    private Double[] getP6() {
        Double[] prev = getP5();
        return new Double[]{prev[0] + wall.weight.getD4() * scale, prev[1]};
    }

    private Double[] getP7() {
        Double[] prev = getP6();
        return new Double[]{prev[0], prev[1] + wall.height.getD3() * scale};
    }
}
