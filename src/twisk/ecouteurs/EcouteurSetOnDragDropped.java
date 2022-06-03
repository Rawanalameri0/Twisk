package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueEtapeIG;
import twisk.vues.VueMondeIG;

public class EcouteurSetOnDragDropped implements EventHandler<DragEvent> {
    private MondeIG monde;
    private VueMondeIG vueMonde;
    public EcouteurSetOnDragDropped(MondeIG monde,VueMondeIG vueMonde){
        this.monde=monde;
        this.vueMonde=vueMonde;
    }
    @Override
    public void handle(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if(dragboard.hasString()){
            String node = dragboard.getString();
            VueEtapeIG vueEtape = (VueEtapeIG)vueMonde.lookup("#"+node);
            if(vueEtape!=null) {
                vueEtape.getEtape().setPosY((int) dragEvent.getY());
                vueEtape.getEtape().setPosX((int) dragEvent.getX());
                vueEtape.getEtape().refaitptdecontrole();
                vueEtape.relocate((int)dragEvent.getX(),(int)dragEvent.getY());

            }
            dragEvent.consume();
            monde.notifierObservateurs();
        }
    }
}
