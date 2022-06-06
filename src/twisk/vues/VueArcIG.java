package twisk.vues;

import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import twisk.ecouteurs.EcouteurArc;
import twisk.mondeIG.*;

/**
 * Classe VueArcIG
 */
public class VueArcIG extends Pane implements Observateur {
    private MondeIG monde;
    private ArcIG arc;
    private Line line;

    /**
     * La constructeur VueArcIG
     * @param monde
     * @param arc
     */
    public VueArcIG(MondeIG monde, ArcIG arc){
        this.monde=monde;
        this.arc=arc;
        this.line=new Line();
        line.setStrokeWidth(3);
        double startx = arc.getpt1().getX();
        double starty= arc.getpt1().getY();
        double endx = arc.getpt2().getX();
        double endy = arc.getpt2().getY();
        line.setStartX(startx);
        line.setStartY(starty);
        line.setEndX(endx);
        line.setEndY(endy);
        Polyline polyline = new Polyline();
        double ang = Math.atan2((endy-starty),(endx-startx))- Math.PI / 2.0;
        double cos = Math.cos(ang);
        double sin = Math.sin(ang);
        double fleche = 14.0;
        double ptx1 = (- 1.0 / 4.0 * cos + Math.sqrt(6) / 8 * sin) * fleche+ endx;
        double pty1 = (- 1.0 / 4.0 * sin - Math.sqrt(6) /8 * cos) * fleche + endy;
        double ptx2 = ( 2.0 / 6.0 * cos + Math.sqrt(6) / 8* sin) * fleche+ endx;
        double pty2 = ( 2.0 / 4.0 * sin - Math.sqrt(6) / 8 * cos) * fleche + endy;
        polyline.getPoints().addAll(ptx1,pty1,ptx2,pty2,endx,endy);
        polyline.setStrokeWidth(3);
        line.setStyle("-fx-stroke: #0B2176");
        line.setOnMouseClicked(new EcouteurArc(monde,this));
        this.getChildren().addAll(polyline,line);
        if(this.arc.isSelect()){
            line.setStyle("-fx-stroke: #1968b8");
            polyline.setStyle("-fx-stroke: #1968b8");
        }

    }

    /**
     * @return getter arc
     */
    public ArcIG getArc() {
        return arc;
    }

    @Override
    public void reagir() {

    }
}
