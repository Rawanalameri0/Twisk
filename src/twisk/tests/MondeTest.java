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
    void ajouter() {
        assertEquals(9,monde.nbEtapes());
    }

    @Test
    void nbGuichets() {
        assertEquals(3,monde.nbGuichets());
    }

    //ce teste est aussi pour la fonction getNumero() de Etape
    @Test
    void iterator() {
        int i=0;
        int cptg=1;
        for (Etape e: monde){
                assertEquals(i,e.getNumero());
            if (e.estUnGuichet()){
                assertEquals(cptg,e.getNumeroGuichet());
                cptg++;
            }
            i++;
        }
        // on a bien un monde qui est iterable
    }

    @Test
    void testNumGuichet(){
        assertEquals(1,fileTob.getNumeroGuichet());
        assertEquals(2,fileBalon.getNumeroGuichet());
        assertEquals(3,fileWC.getNumeroGuichet());
    }
}