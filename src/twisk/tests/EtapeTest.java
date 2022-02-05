package twisk.tests;

import org.junit.jupiter.api.*;
import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {
    Etape FilTicket,Tickets,FileCine,Cine;
    @BeforeEach
    void setUp() {
        FilTicket= new Guichet("File Ticket",4);
        Tickets =new ActiviteRestreinte("Achat Tickets",8,2);
        FileCine=new Guichet("File Cinema",2);
        Cine=new Activite("Cinema",7200,60);
    }

    @Test
    void ajouterSuccesseur() {
        FilTicket.ajouterSuccesseur(Tickets);
        Tickets.ajouterSuccesseur(FileCine);
        FileCine.ajouterSuccesseur(Cine);
        assertEquals(1,FilTicket.nbSuccesseurs());
        assertEquals(1,Tickets.nbSuccesseurs());
        assertEquals(1,FileCine.nbSuccesseurs());
        assertEquals(0,Cine.nbSuccesseurs());
    }


    @Test
    void estUneActivite() {
        assertTrue(Cine.estUneActivite());
        assertTrue(FilTicket.estUnGuichet());
        assertFalse(FileCine.estUneActivite());
        assertFalse(Tickets.estUnGuichet());
    }

    @Test
    void testToString() {
        assertEquals("File Ticket: 4 jetons",FilTicket.toString());
        assertEquals("Cinema: 7200s ± 60s",Cine.toString());
    }
}