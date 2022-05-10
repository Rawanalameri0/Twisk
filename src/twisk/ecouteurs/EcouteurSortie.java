package twisk.ecouteurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Classe EcouteurSortie
 */
public class EcouteurSortie implements EventHandler<ActionEvent> {
    private MondeIG monde;

    /**
     * permet de désigner des activités
      sélectionnées comme des sorties ou de les supprimer si elles sont déjà enregistrées comme sorties
     * @param monde
     */
    public EcouteurSortie(MondeIG monde){
        this.monde=monde;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        this.monde.ajoutsortie();
        this.monde.notifierObservateurs();


    }
}
