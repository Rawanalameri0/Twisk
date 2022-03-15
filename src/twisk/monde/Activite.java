package twisk.monde;

public class Activite extends Etape{
    protected int temps;
    protected int ecartTemps;

    public Activite(String nom){
        super(nom);
        this.temps=4;
        this.ecartTemps=2;
    }

    public Activite(String nom,int t,int e){
        super(nom);
        this.temps=t;
        this.ecartTemps=e;
    }

    @Override
    public boolean estUneActivite(){
        return  true;
    }

    public String toC(){
        return "\ntranfert("+this.nom+","+this.gestSucc.getSuccesseur()+");\n" +
                getDelai();
    }

    public String getDelai(){
        return "delai("+temps+","+ecartTemps+");";
    }
}
