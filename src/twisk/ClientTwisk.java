package twisk;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void initialiserMonde1(Monde monde){
        Etape fileTob,tob,fileBalon,Balonc,plage,fileWC,toilette;
        fileTob=new Guichet("FileToboggon",3);
        tob=new ActiviteRestreinte("Toboggan",2,1);
        fileBalon=new Guichet("FileBalançoire");
        Balonc=new Activite("Balonçoire",8,2);
        plage=new Activite("Plage");
        fileWC=new Guichet("FileWC");
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
    public static void initialiserMonde2(Monde monde){
        Etape FilTicket,Tickets,FileCine,Cine;
        FilTicket= new Guichet("FileTicket",4);
        Tickets =new Activite("AchatTickets",8,2);
        FileCine=new Guichet("FileCinema",2);
        Cine=new Activite("Cinema");
        FilTicket.ajouterSuccesseur(Tickets);
        Tickets.ajouterSuccesseur(FileCine);
        FileCine.ajouterSuccesseur(Cine);
        monde.ajouter(FilTicket,Tickets,FileCine,Cine);
        monde.aCommeEntree(FilTicket);
        monde.aCommeSortie(Cine);
    }

    public static void initMonde(Monde monde){
        Etape Zoo,guichetZoo;
        guichetZoo=new Guichet("GUICHET_ZOO",2);
        Zoo=new Activite("ZOO",4,2);
        monde.aCommeEntree(guichetZoo);
        monde.aCommeSortie(Zoo);
        guichetZoo.ajouterSuccesseur(Zoo);
        monde.ajouter(guichetZoo,Zoo);
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde = new Monde();
        initMonde(monde);
        sim.setNbClients(5);
        sim.simuler(monde);
    }
}