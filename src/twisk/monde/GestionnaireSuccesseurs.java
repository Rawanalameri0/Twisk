package twisk.monde;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Classe GestionnaireSuccesseurs
 * @author IKHRICHI SOUMAYA AL-AMERI RAWAN
 */
public class GestionnaireSuccesseurs implements Iterable<Etape> {
    private ArrayList<Etape> etapes;

    /**
     * La constructeur de GestionnaireSuccesseurs
     */
    public GestionnaireSuccesseurs(){
        this.etapes=new ArrayList<Etape>();
    }

    /**
     * Ajoute de successeurs au etapes
     * @param etapes
     */
    public void ajouter(Etape... etapes){
        for(Etape e: etapes){
            this.etapes.add(e);
        }
    }

    /**
     * @return le nombre des etapes
     */
    public int nbEtapes(){
        return this.etapes.size();
    }

    /**
     * @return l'iteratuer de étape
     */
    @Override
    public Iterator<Etape> iterator() {
        return this.etapes.iterator();
    }

    /**
     * @return une chaine de caractères qui décrire les étapes de successeur
     */
    public String toString(){
        StringBuilder succ=new StringBuilder();
        String s;
        for (int i=0;i<etapes.size();i++){
            if (i==this.nbEtapes()-1){
                succ.append(etapes.get(i).nom);
            }else {
                succ.append(etapes.get(i).nom+"-");
            }
        }
        s=new String(succ);
        return this.nbEtapes()+" successeur "+"-"+s;
    }

    /**
     * @return le nom de successeur
     */
    public String getNomSuccesseur(){
        return etapes.get(0).nom;
    }

    /**
     * @return un getter de successeur
     */
    public Etape getSuccesseur(){
        return etapes.get(0);
    }
}
