package bowling;

public class Lancer {
    private int nbQuille;

    public Lancer(int nbQuille){
        if (nbQuille > 10) throw new IllegalArgumentException("c'est 10 max");
        if (nbQuille < 0) throw new IllegalArgumentException("c'est 0 min");
        this.nbQuille=nbQuille;
    }
    public int getNbQuille() {
        return nbQuille;
    }

}
