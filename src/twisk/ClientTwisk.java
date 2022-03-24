package twisk;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class ClientTwisk {

    public static void initialiserMonde(Monde monde){
        Etape Tickets,Cine,FileCafet,Cafet;
        FileCafet=new Guichet("FileCafet",4);
        Cafet=new ActiviteRestreinte("Cafeteria",6,3);
        Tickets =new Guichet("Tickets",2);
        Cine=new Activite("Cinema");
        FileCafet.ajouterSuccesseur(Cafet);
        Cafet.ajouterSuccesseur(Tickets);
        Tickets.ajouterSuccesseur(Cine);
        monde.ajouter(FileCafet,Cafet,Tickets,Cine);
        monde.aCommeEntree(FileCafet);
        monde.aCommeSortie(Cine);
    }


    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde = new Monde();
        initialiserMonde(monde);
        sim.setNbClients(5);
        sim.simuler(monde);
    }
}

