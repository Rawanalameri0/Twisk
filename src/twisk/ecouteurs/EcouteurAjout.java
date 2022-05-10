package twisk.ecouteurs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
/**
 * Classe EcouteurAjout
 * @author AL-AMERI RAWAN
 */
public class EcouteurAjout implements EventHandler<ActionEvent> {
    private MondeIG monde;


    /**
     * La constructeur EcouteurAjout qui ajout une activite dans le monde
     * @param monde
     */
    public EcouteurAjout(MondeIG monde) {
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
