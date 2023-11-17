package bowling;

import java.util.ArrayList;

public class Tour {

    protected TYPE_TOUR typeTour;
    protected boolean fini;
    protected ArrayList<Lancer> lancers;
    protected int score;
    protected int nbBonus;

    public Tour() {
        fini=false;
        lancers = new ArrayList<Lancer>();
        score=0;
        typeTour=TYPE_TOUR.normal;
        nbBonus=0;
	}

    public void addLancer(Lancer a){
        if (fini) {
            //erreur
        }
        if (!lancers.isEmpty() && a.getNbQuille()+lancers.get(0).getNbQuille()>10){
            //erreur
        }
        if (lancers.isEmpty()) {
            if (a.getNbQuille()==10){
                fini=true; 
                typeTour= TYPE_TOUR.strike;
            }
            if (a.getNbQuille()!=10){
                //rien
            }
        }else{
            if (a.getNbQuille()+lancers.get(0).getNbQuille()==10){
                fini=true; 
                typeTour= TYPE_TOUR.spare;
            } else {
                fini=true; 
                typeTour= TYPE_TOUR.normal;
            }
        }
        lancers.add(a);
        score=score + a.getNbQuille();
    }

    public void scoreBonus(int nb){
        if (typeTour==TYPE_TOUR.normal){
            //erreur
        }
        if (nbBonus>=1 && typeTour==TYPE_TOUR.spare){
            //erreur
        }
        if (nbBonus>=2 && typeTour==TYPE_TOUR.strike){
            //erreur
        }
        score=score+nb;
        nbBonus=nbBonus+1;
    }


    public boolean getFini(){
        return fini;
    }
    public int getScore(){
        return score;
    }
    public TYPE_TOUR getTypeTour(){
        return typeTour;
    }
    public int GetLancers(){
        return lancers.size();
    }

}
