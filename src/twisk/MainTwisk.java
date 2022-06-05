package twisk;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

import java.io.IOException;

/**
 * Classe principale
 */
public class MainTwisk extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        MondeIG monde = new MondeIG();
        VueOutils vueoutils = new VueOutils(monde);
        VueMondeIG vuemonde = new VueMondeIG(monde);
        VueMenu menu = new VueMenu(monde);
        root.setTop(menu);
        root.setBottom(vueoutils);
        root.setCenter(vuemonde);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main (String[] args) {
        launch();
    }
}
