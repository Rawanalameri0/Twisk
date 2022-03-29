package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.simulation.Client;

import static com.sun.tools.doclint.Entity.Eta;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    Client client1;
    Client client2;
    Etape act;


    @BeforeEach
    void setUp() {
        client1=new Client(2);
        client2 = new Client(3);
        act = new Activite("Cinema");

    }

    @Test
    void allerA() {
        client1.allerA(act,2);
        assertEquals("Cinema",client1.getEtape().NameEtape() );
        assertEquals(2,client1.getRang());

    }
}