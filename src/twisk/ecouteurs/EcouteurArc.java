package twisk.ecouteurs;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueArcIG;

/**
 * Classe EcouteurArc
 * @author AL-AMERI RAWAN
 */
public class EcouteurArc implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private VueArcIG arc;
    private Line line;
    private Polyline polyline;

    /**
     * La constructeur EcouteurArc qui activer la sélection d’une étape
     * @param monde
     * @param arc
     */
    public EcouteurArc(MondeIG monde, VueArcIG arc){
        this.monde=monde;
        this.arc=arc;

    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==1){
            this.arc.getArc().setSelect(true);
            this.monde.notifierObservateurs();
        }
    }
}
