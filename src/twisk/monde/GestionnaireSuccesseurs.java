package twisk.monde;
import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape> {
    private ArrayList<Etape> etapes;

    /**
     * Constructeur
     */
    public GestionnaireSuccesseurs(){
        this.etapes=new ArrayList<Etape>();
    }

    public void ajouter(Etape... etapes){
        for(Etape e: etapes){
            this.etapes.add(e);
        }
    }

    public int nbEtapes(){
        return this.etapes.size();
    }

    @Override
    public Iterator<Etape> iterator() {
        return this.etapes.iterator();
    }

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

    public String getNomSuccesseur(){
        return etapes.get(0).nom;
    }

    public Etape getSuccesseur(){
        return etapes.get(0);
    }
}
