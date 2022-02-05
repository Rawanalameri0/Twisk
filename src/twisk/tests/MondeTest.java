package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {
    Monde monde;
    Etape fileTob,tob,fileBalon,Balonc,plage,fileWC,toilette;
    @BeforeEach
    void setUp(){
        monde=new Monde();
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

    @Test
    void aCommeEntree() {
        assertEquals(2,monde.NbEntrees());
    }

    @Test
    void aCommeSortie() {
        assertEquals(3,monde.NbSorties());
    }

    @Test
    void ajouter() {
        assertEquals(7,monde.nbEtapes());
    }

    @Test
    void nbGuichets() {
        assertEquals(3,monde.nbGuichets());
    }

    @Test
    void iterator() {
        for (Etape e: monde){
            //System.out.println(e.toString());
        }
        // on a bien un monde qui est iterable
    }
}