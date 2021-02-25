package méthodes;

import java.util.ArrayList;
import java.util.LinkedList;

import sacADos.Objets;

public class Exacte_PSE {
	float poids_maximal;
	ArrayList<Objets> ListObjet; 
	ArrayList<Objets> ListObjetChoisi;
	TriRapide tri;
	LinkedList<Noeud> ListNoeud; //Liste de Noeud traite
	
	public Exacte_PSE(ArrayList<Objets> objetsPresents, ArrayList<Objets> objetsSelectionnée, float poids_maximal) {
		this.ListObjet = objetsPresents;
		this.ListObjetChoisi = objetsSelectionnée;
		this.poids_maximal = poids_maximal;
		tri = new TriRapide(ListObjet);
	}
	
	//methode PSE
	public void methodePSE() {
		tri.triRapide();
		Noeud racine = new Noeud(ListObjet, poids_maximal); // créer le le racine de l'arbre
		racine.setBorneSupRacine(); //mis à jour son borne superieur
		ListNoeud = new LinkedList<Noeud>(); // creer le liste de noeud pour traite son prioritaire
		ListNoeud.add(racine); // ajouter le Racine dans le liste
		NoeudPrioritaire(); //determiner le prioritaire de noeud selon leurs borne superieur
	}
	
	//Tri à bulle le Liste de noeud dans l'ordre decroissant selon leur borne superieur
	public void NoeudPrioritaire() {
		for (int i = 0; i < ListNoeud.size() - 1; i++) {
			for(int j = i + 1; j < ListNoeud.size(); j++) {
				if(ListNoeud.get(j).getBorneSup() > ListNoeud.get(i).getBorneSup())
					echanger(ListNoeud.get(j), ListNoeud.get(i));
			}
		}
		NouveauNoeud(); // Créer des nouveaux noeuds pour celle qui a un plus grand borne sup.
	}
	
	//
	public void NouveauNoeud() {
		//verifier si la profondeur de noeud a depassée le nombre d'objet presente 
		if(ListNoeud.getFirst().getProfondeur() < ListObjet.size()) { 
			if(ListNoeud.getFirst().verifierPoids()) { // Verifier s'il a depasse le poids
				Noeud noeudAjouter = new Noeud(ListNoeud.getFirst()); // Créer un nouveau noeud qui va ajouter l'objet
				noeudAjouter.noeudAjouterObjet(ListNoeud); // Mis à jour infos du noeud
			}
			
			Noeud noeudSansAjouter = new Noeud(ListNoeud.getFirst()); // Créer un nouveau noeud qui n'ajoute pas l'objet
			noeudSansAjouter.noeudSansAjouterObjet(ListNoeud); // Mis à jour infos du noeud
			ListNoeud.removeFirst(); // Supprimer la noeud qui ont deja créer les fils 
			NoeudPrioritaire(); // Recommencer l'etape
		}else {
			for(Objets o : ListNoeud.getFirst().getObjetParNoeud()) // Obtient les objets de noeud qui a un meilleur borne sup.
			ListObjetChoisi.add(o);
		}
	}
	
	

	public void echanger(Noeud x, Noeud y) {
		int x1 = ListNoeud.indexOf(x);
		int y1 = ListNoeud.indexOf(y);
		ListNoeud.set(x1, y);
		ListNoeud.set(y1, x);
	}
	
	
}
