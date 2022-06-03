package twisk.ecouteurs;
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
        System.out.println("create on drag");
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("on drag handle");
        Dragboard dragboard = vueEtape.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(vueEtape.getId());
        dragboard.setContent(clipboardContent);
        mouseEvent.consume();

    }
}
