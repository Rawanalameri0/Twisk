package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueEtapeIG;
import twisk.vues.VueMondeIG;

public class EcouteurSetOnDragOver implements EventHandler<DragEvent> {
    private MondeIG monde;
    private VueMondeIG vueMonde;
    public EcouteurSetOnDragOver(MondeIG monde,VueMondeIG vueMonde){
        this.monde=monde;
        this.vueMonde=vueMonde;
    }
    @Override
    public void handle(DragEvent dragEvent) {
        if(dragEvent.getDragboard().hasString()){
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        }
        dragEvent.consume();
    }
}
