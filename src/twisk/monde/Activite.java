package twisk.monde;

public class Activite extends Etape{

    private int temps;
    private int ecartTemps;

    /**
     * Constructeur d'Activite
     * @param nom le nom de l'activité
     */
    public Activite(String nom){
        super(nom);
        this.temps=4;
        this.ecartTemps=2;
    }

    /**
     * Constructeur d'Activivté
     * @param nom le nom de l'activité
     * @param t le temps (en seconde)
     * @param e l'ecart temps (en seconde)
     */
    public Activite(String nom,int t,int e){
        super(nom);
        this.temps=t;
        this.ecartTemps=e;
    }

    /**
     * @return vrai puisque on est dans la classe Activité
     */
    @Override
    public boolean estUneActivite(){
        return  true;
    }

    /**
     * cette fonction génère le code C pour les activités
     * @return une chaine de carctères
     */
    public String toC(){
        return "\n   "+getDelai()+"\n"+"   transfert("+this.getNom()+","+this.gestSucc.getSuccesseur().getNom()+");" +
                this.gestSucc.getSuccesseur().toC();
    }

    /**
     * @return une chaine de caractères de la fonction en C delai() avec le temps et l'ecart temps
     */
    public String getDelai(){
        return "delai("+temps+","+ecartTemps+");";
    }
}
