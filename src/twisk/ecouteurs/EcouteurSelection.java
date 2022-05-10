package twisk.ecouteurs;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueEtapeIG;

/**
 * Classe EcouteurSelection
 */
public class EcouteurSelection implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private VueEtapeIG vueEtape;

    /**
     * Activer la sélection d’une étape
     * @param monde
     * @param vueEtape
     */
    public EcouteurSelection(MondeIG monde, VueEtapeIG vueEtape){
    this.monde=monde;
    this.vueEtape = vueEtape;
}
    @Override
    public void handle(MouseEvent mouseEvent) {
      if(mouseEvent.getClickCount()==1){
          this.monde.addEtapeSelect(this.vueEtape.getEtape());
      }
      this.monde.notifierObservateurs();



    }
}
