package twisk;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class ClientTwisk {

    public static void initialiserMonde(Monde monde){
        Etape Tickets,Cine,FileCafet,Cafet;
        FileCafet=new Guichet("1File Cafet",4);
        Cafet=new ActiviteRestreinte("Caféteria",6,3);
        Tickets =new Guichet("Guichet-Tickets",2);
        Cine=new Activite("Cinéma");
        FileCafet.ajouterSuccesseur(Cafet);
        Cafet.ajouterSuccesseur(Tickets);
        Tickets.ajouterSuccesseur(Cine);
        monde.ajouter(FileCafet,Cafet,Tickets,Cine);
        monde.aCommeEntree(FileCafet);
        monde.aCommeSortie(Cine);
    }

    
    public void intialiserMonde2(Monde monde){
        Etape fileTob,tob,fileBalon,Balonc,plage,fileWC,toilette;
        fileTob=new Guichet("File Toboggon",3);
        tob=new ActiviteRestreinte("Toboggan",2,1);
        fileBalon=new Guichet("File Balançoire");
        Balonc=new Activite("Balonçoire",8,2);
        plage=new Activite("Plage");
        fileWC=new Guichet("File WC");
        toilette=new Activite("Toilette");
        fileTob.ajouterSuccesseur(tob);
        fileBalon.ajouterSuccesseur(Balonc);
        tob.ajouterSuccesseur(plage);
        Balonc.ajouterSuccesseur(plage,fileWC);
        fileWC.ajouterSuccesseur(toilette);
        monde.ajouter(fileTob,tob,fileBalon,Balonc,plage,fileWC,toilette);
        monde.aCommeEntree(fileTob,fileBalon);
        monde.aCommeSortie(plage,Balonc,toilette);
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde = new Monde();
        initialiserMonde(monde);
        sim.setNbClients(5);
        sim.simuler(monde);
    }
}

