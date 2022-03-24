package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.SasEntree;
import twisk.monde.SasSortie;

import static org.junit.jupiter.api.Assertions.*;

class ActiviteTest {
    Activite activite;
    ActiviteRestreinte a;
    SasEntree e;
    SasSortie s;

    @BeforeEach
    void setUp() {
        e = new SasEntree();
        activite = new Activite("Balonçoire");
        a = new ActiviteRestreinte("Toboggan");
        s=new SasSortie();
        activite.ajouterSuccesseur(s);

    }

    @Test
    void estUneActivite() {
        assertEquals("Balonçoire",activite.getNom());
    }

    @Test
    void toC(){
        String s ="\ndelai(4,2);\n" +
                "transfert(Balonçoire,sasSortie);\n";
       assertEquals(s,activite.toC());
    }




}