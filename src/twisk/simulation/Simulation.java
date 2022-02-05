package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Monde;

public class Simulation {

    public Simulation(){}

    public void simuler(Monde monde){
        System.out.println("Le Monde :");
        for (Etape e:monde){
            if(e.estUnGuichet()){
                System.out.println("Guichet "+e.getNumero()+": "+e.toString());
            }else {
                System.out.println(e.toString());
            }
        }
        System.out.println(monde.toString());
    }

}
