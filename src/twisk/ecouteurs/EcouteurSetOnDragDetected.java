package twisk.Ecouteurs;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueEtapeIG;

/**
 * Classe EcouteurSetOnDragDetected
 */
public class EcouteurSetOnDragDetected implements EventHandler<MouseEvent> {
    private MondeIG monde;
    private VueEtapeIG vueEtape;

    /**
     *
     * @param monde
     * @param vueEtape
     */
    public EcouteurSetOnDragDetected(MondeIG monde , VueEtapeIG vueEtape)
    {
        this.monde=monde;
        this.vueEtape=vueEtape;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        Dragboard dragboard = vueEtape.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(vueEtape.getId());
        dragboard.setContent(clipboardContent);
        mouseEvent.consume();

    }
}
