package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;



public class EcouteurAjoutGuichet implements EventHandler<ActionEvent> {
        private MondeIG monde;
        /**
         * La constructeur EcouteurAjoutGuichet qui ajout un guichet dans le monde
         * @param monde
         */
        public EcouteurAjoutGuichet(MondeIG monde) {
            this.monde = monde;
        }


        @Override
        public void handle(ActionEvent actionEvent) {
            monde.ajouter("Guichet");
            monde.notifierObservateurs();
        }
}

