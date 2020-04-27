package wall;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PrintWall {
    static final String[][] titles = {
            {"משקל מרחבי של הקרקע", "t/m^3", "", "G "},
            {"זזוית חיכוך פנימית", "", "", "φ "},
            {"זוית חיכוך קרקע-גב הקיר", "", "", "λ "},
            {"שיפוע הקרקע הטבעית במעלות", "", "", "i "},
            {"מאמץ מגע מותר מקסימלי", "", "", "η "},
            {"שיפוע בסיס הקיר במעלות", "", "", "θ "},
            {"מקדם חיכוך בסיס הקיר-קרקע", "", "", "μ "},
            {"קוהזיה", "", "", "Co"},
            {"עומס מפורס על הקרקע", "", "", "Q "},
            {"משקל מרחבי של הקיר", "", "", "Gw"}
    };
    final Map<TextAttribute, Integer> fontAttributesUnderLine = new HashMap<TextAttribute, Integer>();
    final Map<TextAttribute, Boolean> fontAttributeRegular = new HashMap<TextAttribute, Boolean>();
    public static AnchorPane mainPane;

    public static void print(Pane node) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = scaleX;
        node.getTransforms().add(new Scale(scaleX, scaleY));
        // node.setTranslateX(pageLayout.getPrintableWidth());

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(node.getScene().getWindow()) ){
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
            }
        }
    }



        public static Pane createPrintable(WallController wall) throws IOException {
            Pane h = new Pane();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(PrintWall.class.getResource("newWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(root, 600, 400);
            Stage stage = new Stage();

            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();

            return root;
        }

//        private static void addBasicInfo (WallController wall, GridPane gp){
//            int r = 0;
//            gp.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
//            titles[0][2] = Double.toString(wall.getModel().getGamma());
//            titles[1][2] = Double.toString(wall.getModel().getFi());
//            titles[2][2] = Double.toString(wall.getModel().getLambda());
//            titles[3][2] = Double.toString(wall.getModel().getI());
//            titles[4][2] = Double.toString(wall.getModel().getSigma());
//            titles[5][2] = Double.toString(wall.getModel().alphaProperty().get());
//            titles[6][2] = Double.toString(wall.getModel().getMiu());
//            titles[7][2] = Double.toString(wall.getModel().getCo());
//            titles[8][2] = Double.toString(wall.getModel().getQ());
//            titles[9][2] = Double.toString(wall.getModel().getGw());
//
//            for (String[] title : titles)
//                gp.addRow(r++, new Text(title[0]), new Text(title[1]), new Text(title[2]), new Text("="), new Text(title[3]));
//            gp.setHgap(10);
//        }
//    public int print(Graphics g, PageFormat pf, int page)
//            throws PrinterException {
//
//        // We have only one page, and 'page'
//        // is zero-based
//        if (page > 0) {
//            return NO_SUCH_PAGE;
//        }
//        final int bounds = (int) pf.getImageableWidth() / 12;
//        int right = (int) pf.getImageableWidth() - bounds;
//        int width = (int) pf.getImageableWidth();
//
//        fontAttributesUnderLine.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//        Font underLine = new Font("Narkisim", Font.PLAIN, 12).deriveFont(fontAttributesUnderLine);
//
//        fontAttributeRegular.put(TextAttribute.RUN_DIRECTION, TextAttribute.RUN_DIRECTION_RTL);
//        Font regularFont = new Font("Narkisim", Font.PLAIN, 12).deriveFont(fontAttributeRegular);
//        int line = 5;
//        // User (0,0) is typically outside the
//        // imageable area, so we must translate
//        // by the X and Y values in the PageFormat
//        // to avoid clipping.
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.translate(pf.getImageableX(), pf.getImageableY());
//        line = printTitles(g, regularFont, width, line);
//        line += 2; // Jump 2 lines
//        for (Shape s : new WallGraphics(this).s) {
//
//        }
//        line = PrintBasicInfo(g, right, width, regularFont, underLine, line);
//        line += 2;
//        line = printDimensions(g, right, width, regularFont, underLine, line);
//        // Now we perform our rendering
//
//        // tell the caller that this page is part
//        // of the printed document
//        return PAGE_EXISTS;
//    }

   /* private int printTitles(Graphics g, Font regularFont, int width, int line) {
        g.setFont(regularFont);
        drawStringCenterRTL(g, "שם הפרויקט: " + this.Name, width, line++ * g.getFontMetrics().getHeight());
        drawStringCenterRTL(g, "בדיקת קיר תומך לגובה " + this.height.getHTotal() + " מ'. החישוב לפי קולון", width, line++ * g.getFontMetrics().getHeight());
        return line;
    }

    private int PrintBasicInfo(Graphics g, int right, int width, Font regularFont, Font underLineFont, int line) {
        g.setFont(underLineFont);
        drawStringCenterRTL(g, "נתונים כללים:", width, line++ * g.getFontMetrics().getHeight());
        line++;
        g.setFont(regularFont);
        int lineHeight = g.getFontMetrics().getHeight();
        int maxWidth = 0;
        for (String[] s : titles) {
            if (g.getFontMetrics().stringWidth(s[0]) > maxWidth)
                maxWidth = g.getFontMetrics().stringWidth(s[0]);
        }
        for (String[] s : titles) {

            drawStringRTL(g, s[0], right, line * lineHeight);                       // title
            drawStringRTL(g, s[1], right - maxWidth - 20, line * lineHeight);    // Measures
            drawStringRTL(g, "=", right - maxWidth - 100, line * lineHeight); // '=' symbol
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            drawStringRTL(g, s[2], right - maxWidth - 130, line * lineHeight);   // Letter
            g.setFont(regularFont);
            line++;
        }
        line -= titles.length;
        drawStringRTL(g, Double.toString(this.getGamma()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getFi()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getLambda()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getI()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getSigma()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getAlpha()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getMiu()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getCo()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getQ()), right - maxWidth - 60, line++ * lineHeight);
        drawStringRTL(g, Double.toString(this.getGw()), right - maxWidth - 60, line++ * lineHeight);

        return line;
    }

    private int printDimensions(Graphics g, int right, int width, Font regularFont, Font underLineFont, int line) {
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        int lineHeight = g.getFontMetrics().getHeight();
        for (int i = 0; i <= 4; i++) {
            g.drawString("H" + (i == 0 ? "" : i), 50, line * lineHeight);
            g.drawString("=", 80, line * lineHeight);
            g.drawString("W" + (i == 0 ? "" : i), 200, line * lineHeight);
            g.drawString("=", 230, line * lineHeight);

            line++;
        }
        return line;
    }*/
    }
