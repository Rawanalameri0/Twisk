package twisk.outils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
/**
 * Classe KitC
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class KitC {
    private FabriqueNumero fabriqueNumero= FabriqueNumero.getInstance();
    private int compteur=fabriqueNumero.getCptLibTwisk();
    /**
     * La constructeur de KitC
     */
    public KitC(){

    }

    /**
     * Création de l'environement sous /tmp
     */
    public void creerEnvironement(){
        try {
            // création du répertoire twisk sous /tmp.
            // Ne déclenche pas d’erreur si le répertoire existe déjà
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
            // copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h", "codeNatif.o"};
            for (String nom : liste) {
                InputStream source = getClass().getResource("/codeC/"+nom).openStream();
                File destination = new File("/tmp/twisk/"+nom);
                copier(source,destination);
               // Path source = Paths.get(getClass().getResource("/codeC/"+nom).getPath());
                //Path newdir = Paths.get("/tmp/twisk/");
                //Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Création des fichiers dans tmp/twisk
     * @param codeC un string du code présent dans le monde
     */
    public void creerFichier(String codeC) {
        File file = new File("/tmp/twisk/client.c");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(codeC);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Compliation d'un fichier C
     */
    public void compiler(){
        String commande = "gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o";
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec(commande);
            p.waitFor();
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne ;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Consrtuction de la librairie en utlisant la execution de la commande
     */
    public void construireLibrairie() {
        String commande = "gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk"+compteur+".so";
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec(commande);
            p.waitFor();
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copier les fichiers de ressources dans l'archive jar
     * @param source un fichier source
     * @param dest un fichier destination
     * @throws IOException s'il y a un probléme
     */
    private void copier(InputStream source, File dest) throws IOException {
        InputStream sourceFile = source;
        OutputStream destinationFile = new FileOutputStream(dest) ;
        // Lecture par segment de 0.5Mo
        byte buffer[] = new byte[512 * 1024];
        int nbLecture;
        while ((nbLecture = sourceFile.read(buffer)) != -1){
            destinationFile.write(buffer, 0, nbLecture);
        }
        destinationFile.close();
        sourceFile.close();
    }

    public int getCompteur() {
        return compteur;
    }
}
