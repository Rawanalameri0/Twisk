package twisk.ecouteurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;



/**
 * Classe EcouteurAjoutActivite
 * @author AL-AMERI RAWAN
 */
public class EcouteurAjoutActivite implements EventHandler<ActionEvent> {
    private MondeIG monde;


    /**
     * La constructeur EcouteurAjoutActivite qui ajout une activite dans le monde
     * @param monde
     */
    public EcouteurAjoutActivite(MondeIG monde) {
        this.monde = monde;


    }


    @Override
    public void handle(ActionEvent actionEvent) {
       monde.ajouter("Activite");
       /*System.out.println("Ajoutez une activite");
        for (EtapeIG e:monde){
            System.out.println(e.getnomEtape());
        }
        System.out.println("**********************");*/
        monde.notifierObservateurs();
    }
}
