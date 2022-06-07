package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.simulation.Client;

public class VueClientIG extends Circle implements Observateur{
    private MondeIG monde;
    private Client client;

    private int pos=10;

    public VueClientIG(MondeIG monde, Client client){
        this.monde=monde;
        this.client=client;
        this.setRadius(6);
        double x = 20+pos;
        double y = 40+pos;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setFill(Color.color(Math.random(),Math.random(),Math.random()));
        //monde.notifierObservateurs();
    }

    public void setPos(int pos) {
        this.pos += pos;
    }

    @Override
    public void reagir() {

    }
}
