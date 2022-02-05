package twisk.monde;

public class SasEntree extends Activite{
    public SasEntree(){
        super("");
        this.reset();
    }

    @Override
    public String toString() {
        return gestSucc.toString();
    }
}
