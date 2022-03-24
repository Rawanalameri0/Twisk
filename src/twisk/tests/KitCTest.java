package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.KitC;



class KitCTest {
    KitC kitc;
    @BeforeEach
    void setUp(){
        kitc=new KitC();
    }


    @Test
    void creerEnvironement() {
        kitc.creerEnvironement();
    }

    @Test
    void creerFichier() {
        String codeC="#include \"def.h\"\n#define entree 0\n#define sortie 1\n#define activite 2\n" +
                "void simulation(int ids){\n entrer(entree);\ndelai(4,2);\ntransfert(entree,activite);\n" +
                "delai(6,3);\ntransfert(activite,sortie);\n}";
        kitc.creerFichier(codeC);
    }

    @Test
    void compiler() {
        kitc.compiler();
    }

    @Test
    void construireLibrairie() {
        kitc.construireLibrairie();
    }
}