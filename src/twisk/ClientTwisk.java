package twisk;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void initialiserMonde1(Monde monde){
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
    public static void initialiserMonde2(Monde monde){
        Etape FilTicket,Tickets,FileCine,Cine;
        FilTicket= new Guichet("File Ticket",4);
        Tickets =new Activite("Achat Tickets",8,2);
        FileCine=new Guichet("File Cinema",2);
        Cine=new Activite("Cinema");
        FilTicket.ajouterSuccesseur(Tickets);
        Tickets.ajouterSuccesseur(FileCine);
        FileCine.ajouterSuccesseur(Cine);
        monde.ajouter(FilTicket,Tickets,FileCine,Cine);
        monde.aCommeEntree(FilTicket);
        monde.aCommeSortie(Cine);
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde1 = new Monde();
        initialiserMonde1(monde1);
        System.out.println("Parc: ");
        sim.simuler(monde1);
    }
}