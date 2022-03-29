package twisk.tests;

import org.junit.jupiter.api.*;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Etape;
import twisk.simulation.Client;
import twisk.simulation.GestionnairesClients;

import static org.junit.jupiter.api.Assertions.*;

class GestionnairesClientsTest {
    GestionnairesClients gestClient;
    Etape etape;

    @BeforeEach
    void setUp() {
        gestClient=new GestionnairesClients(5);
        etape=new ActiviteRestreinte("Toboggan");
        gestClient.setClients(1001,1002,1003,1004,1005);
    }

    @Test
    void setClients(){
        assertEquals(5,gestClient.getNbClients());
        int numero=1001;
        for (Client c:gestClient){
            assertEquals(numero,c.getNumeroClient());
            numero++;
        }
    }

    @Test
    void nettoyer() {
        gestClient.nettoyer();
        assertEquals(0,gestClient.getNbClients());
    }

}