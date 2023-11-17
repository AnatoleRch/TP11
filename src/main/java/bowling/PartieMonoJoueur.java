package bowling;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	private ArrayList<Tour> LesTours;
	private int indiceTour;

	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		LesTours = new ArrayList<Tour>();
		for (int i=0 ; i<10 ;i++){
			LesTours.add(new Tour());
		}
		LesTours.add(new DernierTour());
		indiceTour=0;
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (indiceTour==10 && LesTours.get(10).getFini()){
			throw new IllegalStateException("La partie est finie rentre chez toi");
		}
		LesTours.get(indiceTour).addLancer(new Lancer(nombreDeQuillesAbattues));

		// gestion score spare/strike
		

		if (indiceTour>0){
			if (LesTours.get(indiceTour) instanceof DernierTour){
			if (LesTours.get(indiceTour).GetLancers()==1){

				if(LesTours.get(indiceTour-2).getTypeTour()==TYPE_TOUR.strike){
					LesTours.get(indiceTour-2).scoreBonus(nombreDeQuillesAbattues);
				}
				
			}

			} else if (LesTours.get(indiceTour-1).getTypeTour()==TYPE_TOUR.strike){
				LesTours.get(indiceTour-1).scoreBonus(nombreDeQuillesAbattues);

				if (LesTours.get(indiceTour).GetLancers()==1){
					if (indiceTour>1)if(LesTours.get(indiceTour-2).getTypeTour()==TYPE_TOUR.strike){
						LesTours.get(indiceTour-2).scoreBonus(nombreDeQuillesAbattues);
					}
				}
			
			} else if (LesTours.get(indiceTour-1).getTypeTour()==TYPE_TOUR.spare) {
				if (LesTours.get(indiceTour).GetLancers()==1){
					LesTours.get(indiceTour-1).scoreBonus(nombreDeQuillesAbattues);
					
				}
			}
		}

		//fini
		if (LesTours.get(indiceTour).getFini()){
			indiceTour=indiceTour+1;
			
			return false;
			
		}else{
			
			return true;
		}
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {
		int tot=0;
		for (Tour t : LesTours){
			tot=tot+t.getScore();
		}
		return tot;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		if (indiceTour==10 && LesTours.get(9).getFini()){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		if(indiceTour==10)return 0;
		return indiceTour+1;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		return LesTours.get(indiceTour).GetLancers()+1;
	}

}
