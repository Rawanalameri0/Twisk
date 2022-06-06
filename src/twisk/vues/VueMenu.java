package twisk.vues;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import twisk.ecouteurs.*;
import twisk.mondeIG.MondeIG;

/**
 * classe VueMenu
 */

public class VueMenu extends MenuBar implements Observateur {
    private MondeIG monde;

    /**
     * La constructeur de VueMenu
     * @param monde
     */
    public VueMenu(MondeIG monde){
        this.monde=monde;
        Menu fichier = new Menu("Fichier");
        Menu edition = new Menu("Edition");
        Menu Monde = new Menu("Monde");
        Menu Parametres = new Menu("Paramètres");
        MenuItem quitter = new MenuItem("Quitter");
        quitter.setStyle("-fx-font-weight: bold;-fx-text-fill:#0b2345");
        quitter.setOnAction(new EcouteurQuitter(this.monde));
        MenuItem supprimer = new MenuItem("Supprimer");
        supprimer.setStyle("-fx-font-weight: bold;-fx-text-fill:#0b2345");
        supprimer.setOnAction(new EcouteurSupprimer(this.monde));
        MenuItem renommer = new MenuItem("Renommer");
        renommer.setOnAction(new EcouteurRenommer(this.monde));
        MenuItem effacerselection = new MenuItem("Désactiver la sélection");
        effacerselection.setOnAction(new EcouteurDesactivrselect(this.monde));
        MenuItem entree = new MenuItem("Entrée");
        entree.setOnAction(new EcouteurEntree(this.monde));
        MenuItem sortie = new MenuItem("Sortie");
        sortie.setOnAction(new EcouteurSortie(this.monde));
        MenuItem delai = new MenuItem("délai");
        delai.setOnAction(new EcouteurDelai(monde));
        MenuItem ecrattemps = new MenuItem("écart-temps");
        MenuItem nbJetons = new MenuItem("nombre de jetons");
        ecrattemps.setOnAction(new EcouteurEcart(this.monde));
        nbJetons.setOnAction(new EcouteurNbJetons(this.monde));
        fichier.getItems().addAll(quitter);
        edition.getItems().addAll(supprimer,renommer,effacerselection);
        Monde.getItems().addAll(entree,sortie);
        Parametres.getItems().addAll(delai,ecrattemps,nbJetons);
        this.setStyle("-fx-font-weight: bold; -fx-text-fill: white");
        this.getMenus().addAll(fichier,edition,Monde,Parametres);
        this.monde.ajouterObservateur(this);
    }
    @Override
    public void reagir() {

    }


}
