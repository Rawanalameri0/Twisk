package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.mondeIG.MondeIG;
import twisk.simulation.Client;

public class VueClientIG extends Circle implements Observateur{
    private MondeIG monde;
    private Client client;

    public VueClientIG(MondeIG monde,Client client){
        this.monde=monde;
        this.client=client;
        this.setRadius(6);
        //this.setCenterX(monde.getCorrespondanceEtapes().getEtapeIG(client.getEtape()).getPosX());
        //this.setCenterY(monde.getCorrespondanceEtapes().getEtapeIG(client.getEtape()).getPosY());
        this.setFill(Color.color(Math.random(),Math.random(),Math.random()));
    }

    @Override
    public void reagir() {

    }
}
