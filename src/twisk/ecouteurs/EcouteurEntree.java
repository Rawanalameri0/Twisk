package twisk.ecouteurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import java.util.Iterator;

/**
 * Classe EcouteurEntree
 * @author AL-AMERI RAWAN
 */
public class EcouteurEntree implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * La constructeur EcouteurEntree qui permet de désigner des activités sélectionnées
     * comme des entrées ou de les supprimer si elles sont déjà enregistrées comme entrées
     *
     * @param monde
     */
    public EcouteurEntree(MondeIG monde) {
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajoutentree();
        this.monde.notifierObservateurs();
    }
}