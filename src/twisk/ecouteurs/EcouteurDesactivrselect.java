package twisk.ecouteurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import java.util.Iterator;

/**
 * Classe EcouteurDesactivrselect
 * @author AL-AMERI RAWAN
 */
public class EcouteurDesactivrselect implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * La constructeur EcouteurDesactivrselect qui désactive  la sélection
     * @param monde
     */
    public EcouteurDesactivrselect(MondeIG monde){
        this.monde = monde;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Iterator<ArcIG> arcIGIterator = monde.iteratorarc();
        while (arcIGIterator.hasNext()){
            ArcIG arc = arcIGIterator.next();
            if(arc.isSelect()){
                arc.setSelect(false);
            }
        }
        this.monde.desactiverSelection();
        this.monde.notifierObservateurs();
    }
}
