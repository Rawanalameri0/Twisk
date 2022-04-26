package twisk;

import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientTwisk {

    public static void initialiserMonde1(Monde monde){
        Etape Tickets,Cine,FileCafet,Cafet;
        FileCafet=new Guichet("File Cafet",4);
        Cafet=new ActiviteRestreinte("Caféteria",6,3);
        Tickets =new Guichet("Guichet-Tickets",2);
        Cine=new Activite("Cinéma");
        FileCafet.ajouterSuccesseur(Cafet);
        Cafet.ajouterSuccesseur(Tickets);
        Tickets.ajouterSuccesseur(Cine);
        monde.ajouter(FileCafet,Cafet,Tickets,Cine);
        monde.aCommeEntree(FileCafet);
        monde.aCommeSortie(Cine);
        ClassLoaderPerso classloader = new ClassLoaderPerso(ClientTwisk.class.getClass().getClassLoader());
        try {
            Class<?> classsimulation = classloader.loadClass("twisk.simulation.Simulation");
            Object simul= classsimulation.getConstructor().newInstance();
            Method setnbclients = simul.getClass().getDeclaredMethod("setNbClients", int.class);
            Method simuler = simul.getClass().getDeclaredMethod("simuler",twisk.monde.Monde.class);
            setnbclients.invoke(simul,5);
            simuler.invoke(simul,monde);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    
    public static void initialiserMonde2(Monde monde){
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
        ClassLoaderPerso classloader = new ClassLoaderPerso(ClientTwisk.class.getClass().getClassLoader());
        try {
            Class<?> classsimulation = classloader.loadClass("twisk.simulation.Simulation");
            Object simul= classsimulation.getConstructor().newInstance();
            Method setnbclients = simul.getClass().getDeclaredMethod("setNbClients", Integer.class);
            Method simuler = simul.getClass().getDeclaredMethod("simuler",twisk.monde.Monde.class);
            setnbclients.invoke(simul,5);
            simuler.invoke(simul,monde);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        Monde monde = new Monde();
        initialiserMonde1(monde);
        sim.setNbClients(5);
        sim.simuler(monde);
        Monde monde1= new Monde();
        initialiserMonde2(monde1);
        sim.simuler(monde1);
    }
}

