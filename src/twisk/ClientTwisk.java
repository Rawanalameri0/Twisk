package twisk;

import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import java.lang.reflect.*;

public class ClientTwisk {

    public static void initialiserMonde1(Monde monde,int nbClients){
        Etape Tickets,Cine,FileCafet,Cafet;
        FileCafet=new Guichet("File Cafet",4);
        Cafet=new ActiviteRestreinte("Caféteria",6,3);
        Tickets =new Guichet("Guichet Cinéma",2);
        Cine=new ActiviteRestreinte("Cinéma",5,3);
        FileCafet.ajouterSuccesseur(Cafet);
        Cafet.ajouterSuccesseur(Tickets);
        Tickets.ajouterSuccesseur(Cine);
        monde.ajouter(FileCafet,Cafet,Tickets,Cine);
        monde.aCommeEntree(FileCafet);
        monde.aCommeSortie(Cine);
        try {
            ClassLoaderPerso classloader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> simulation = classloader.loadClass("twisk.simulation.Simulation");
            Object simul= simulation.getDeclaredConstructor().newInstance();
            Method setnbclients = simulation.getDeclaredMethod("setNbClients", int.class);
            Method simuler = simulation.getDeclaredMethod("simuler",twisk.monde.Monde.class);
            setnbclients.invoke(simul,nbClients);
            simuler.invoke(simul,monde);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    
    public static void initialiserMonde2(Monde monde1,int nbClients){
        Etape fileTob,tob,fileBalon,Balonc,plage,fileWC,toilette;
        fileTob=new Guichet("File Toboggon",3);
        tob=new ActiviteRestreinte("Toboggan",2,1);
        fileBalon=new Guichet("File Balançoire");
        Balonc=new ActiviteRestreinte("Balonçoire",8,2);
        plage=new Activite("Plage");
        fileWC=new Guichet("File WC");
        toilette=new ActiviteRestreinte("Toilette");
        fileTob.ajouterSuccesseur(tob);
        fileBalon.ajouterSuccesseur(Balonc);
        tob.ajouterSuccesseur(plage);
        Balonc.ajouterSuccesseur(plage,fileWC);
        fileWC.ajouterSuccesseur(toilette);
        monde1.ajouter(fileTob,tob,fileBalon,Balonc,plage,fileWC,toilette);
        monde1.aCommeEntree(fileTob,fileBalon);
        monde1.aCommeSortie(plage,Balonc,toilette);
        try {
            ClassLoaderPerso classloader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> simulation = classloader.loadClass("twisk.simulation.Simulation");
            Object simul= simulation.getDeclaredConstructor().newInstance();
            Method setnbclients = simulation.getDeclaredMethod("setNbClients", int.class);
            Method simuler = simulation.getDeclaredMethod("simuler",twisk.monde.Monde.class);
            setnbclients.invoke(simul,nbClients);
            simuler.invoke(simul,monde1);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void simulationMonde(Monde monde, int nbClients){
        try {
            ClassLoaderPerso classloader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> simulation = classloader.loadClass("twisk.simulation.Simulation");
            Object simul= simulation.getDeclaredConstructor().newInstance();
            Method setnbclients = simulation.getDeclaredMethod("setNbClients", int.class);
            Method simuler = simulation.getDeclaredMethod("simuler",twisk.monde.Monde.class);
            setnbclients.invoke(simul,nbClients);
            simuler.invoke(simul,monde);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
           e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // premier Monde
        Monde monde = new Monde();
        Etape Tickets,Cine,FileCafet,Cafet;
        FileCafet=new Guichet("File Cafet",4);
        Cafet=new ActiviteRestreinte("Caféteria",6,3);
        Tickets =new Guichet("Guichet Cinéma",2);
        Cine=new ActiviteRestreinte("Cinéma",5,3);
        FileCafet.ajouterSuccesseur(Cafet);
        Cafet.ajouterSuccesseur(Tickets);
        Tickets.ajouterSuccesseur(Cine);
        monde.ajouter(FileCafet,Cafet,Tickets,Cine);
        monde.aCommeEntree(FileCafet);
        monde.aCommeSortie(Cine);
        try {
            ClassLoaderPerso classloader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> simulation = classloader.loadClass("twisk.simulation.Simulation");
            Object simul= simulation.getDeclaredConstructor().newInstance();
            Method setnbclients = simulation.getDeclaredMethod("setNbClients", int.class);
            Method simuler = simulation.getDeclaredMethod("simuler",twisk.monde.Monde.class);
            setnbclients.invoke(simul,3);
            simuler.invoke(simul,monde);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // Deuxieme monde
        Monde monde1=new Monde();
        Etape fileTob,tob,fileBalon,Balonc,plage,fileWC,toilette;
        fileTob=new Guichet("File Toboggon",3);
        tob=new ActiviteRestreinte("Toboggan",2,1);
        fileBalon=new Guichet("File Balançoire");
        Balonc=new ActiviteRestreinte("Balonçoire",8,2);
        plage=new Activite("Plage");
        fileWC=new Guichet("File WC");
        toilette=new ActiviteRestreinte("Toilette");
        fileTob.ajouterSuccesseur(tob);
        fileBalon.ajouterSuccesseur(Balonc);
        tob.ajouterSuccesseur(plage);
        Balonc.ajouterSuccesseur(plage,fileWC);
        fileWC.ajouterSuccesseur(toilette);
        monde1.ajouter(fileTob,tob,fileBalon,Balonc,plage,fileWC,toilette);
        monde1.aCommeEntree(fileTob,fileBalon);
        monde1.aCommeSortie(plage,Balonc,toilette);
        try {
            ClassLoaderPerso classloader1 = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
            Class<?> simulation1 = classloader1.loadClass("twisk.simulation.Simulation");
            Object simul1= simulation1.getDeclaredConstructor().newInstance();
            Method setnbclients1= simulation1.getDeclaredMethod("setNbClients", int.class);
            Method simuler1 = simulation1.getDeclaredMethod("simuler",twisk.monde.Monde.class);
            setnbclients1.invoke(simul1,3);
            simuler1.invoke(simul1,monde1);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

