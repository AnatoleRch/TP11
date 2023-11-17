package bowling;

public class DernierTour extends Tour{
    private int nbStrike;
    public DernierTour(){
        super();
        this.nbStrike=0;
    }

    public void addLancer(Lancer a){
        if (fini) {
            //erreur
        }
        if (lancers.isEmpty()) {
            if (a.getNbQuille()==10){
                typeTour= TYPE_TOUR.strike;
            }
        }else{
            if (a.getNbQuille()+lancers.get(0).getNbQuille()==10){
                typeTour= TYPE_TOUR.spare;
            } else {
                if (a.getNbQuille()==10){
                    typeTour= TYPE_TOUR.strike;
            }
            }
        }
        if (lancers.size()>=1 && typeTour==TYPE_TOUR.normal){
            fini = true;
        };
        if (lancers.size()>=2){
            fini = true;
        };
        lancers.add(a);
        score=score + a.getNbQuille()+nbStrike*a.getNbQuille();
    
    }
}
